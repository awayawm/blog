// TODO remember me
// TODO password reset
// TODO progress on login
// TODO recaptcha on account creation

package blog

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.JWTCreator
import grails.converters.JSON
import java.util.Date

class AccountController {

    AccountService accountService

    private def JWT_TOKEN_SHORT_DURATION = 20 * 60 * 1000 
    private def JWT_TOKEN_LONG_DURATION = 120 * 60 * 1000

    def login() {
        def account = new Account()
        def token
        Date expiresAt = null

        if(request.method == "POST") {

            if(params.username && params.password) {
                try {
                    account = Account.findByUsername(params.username)
                    if(!account) {
                        return render([success: 'false'] as JSON)
                    }

                    session.account = account

                    if(params.remember_me)
                        expiresAt = new Date(new Date().getTime() + JWT_TOKEN_LONG_DURATION)
                    else
                        expiresAt = new Date(new Date().getTime() + JWT_TOKEN_SHORT_DURATION)

                    try {
                        token  = JWTCreator.init()
                                .withExpiresAt(expiresAt)
                                .sign(Algorithm.HMAC256(System.getenv("SECRET_KEY")));

                        if(account.verifyPassword(params.password)) {
                            session.token = token
                            render([success: 'true'] as JSON)
                        }
                        else {
                            render([success: 'false'] as JSON)
                        }

                    } catch (Exception e){
                        println e.printStackTrace()
                    }

                } catch (Exception e) {
                    println e.printStackTrace()
                    render([success: 'false'] as JSON)
                }

            }
            else
                render([success: 'false'] as JSON)
        }
    }

    def remove() {
        def account
        if (params.id) {
            account = Account.get(params.id)
            try {
            account.delete(flush: true)
            } catch (Exception e) {
                println e.printStackTrace()
            }
            render([success: 'true', data: [ status: 'delete function called' ]] as JSON)
        }
    }

    def index() {

        def roles = ['Admin', 'User']

        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        if(session?.account?.role == 'User')
            return render(controller: "account", view: "indexForUser")

		if(request.method == "GET") {
			def returnValue
			if (params.id) {
                def account = Account.get(params.id)
                    if(account != null)
                        return render(Account.get(params.id) as JSON)
                    else
                        return render([success: 'false'] as JSON)

			}
			else {
				return render(view: "indexForAdmin", model: [roles: roles, accounts : Account.getAll()])
			}
		} else if(request.method == "POST") {

            def account = Account.get(params.id)

            if(params.password) {
                account.password = params.password
            }

            if(params.fullname && params.emailaddress && params.role) {

                account.fullName = params.fullname
                account.emailAddress = params.emailaddress
                account.role = params.role
                try {
                    account.save(flush:true, failOnError:true)
                    return render([success: 'true', data: [account: account]] as JSON)
                }
                catch(Exception e) {
                    println e.printStackTrace()
                }
            }
            else
                return render([success: 'false'] as JSON)
		}
    }

    def create() {

        def admin = Account.findByRole("Admin")

        if(request.method == "GET" && admin != null && session?.account?.role == "Admin") {
            return render(view: "create")
        } else if (request.method == "GET" && !Account.find({})) {
            return render(view: "create")
        } else if((request.method == "POST" && params.username && params.password && session?.account?.role == "Admin") || !Account.find({}   )) {

            def account = new Account(username: params.username, password: params.password, role: 'User')
            try {
                account.save(failOnError: true)
                flash.alert = true
                flash.class = "alert-success"
                flash.message = "Account successfully created!"
            } catch (Exception e) {
                flash.alert = false
                flash.class = "alert-danger"
                flash.message = "Account creation not successful ..."
                println e.printStackTrace()
            }
            return redirect(action: "index")
        }

        return redirect(controller: "account", action: "login")

    }

    def logout() {
        session.account = null
        session.token = null
        flash.alert = true
        flash.class = "alert-success"
        flash.message = "You have been successfully logged out."
        redirect action: "index"
    }
}
