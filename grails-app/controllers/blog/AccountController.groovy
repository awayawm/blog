// TODO add roles, authenticate on admin role

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

        if(request.method == "POST") {

            if(params.username && params.password) {
                try {
                    account = Account.findByUsername(params.username) 
                    if(!account)
                        return render([success: 'false'] as JSON)

                    session.account = account

                    Date expiresAt = new Date(new Date().getTime() + JWT_TOKEN_SHORT_DURATION)
                    try {
                        token  = JWTCreator.init()
                                .withExpiresAt(expiresAt)
                                .sign(Algorithm.HMAC256(System.getenv("SECRET_KEY")));

                        if(account.verifyPassword(params.password)) {
                            session.token = token
                            render([success: 'true'] as JSON)
                        }
                        else
                            render([success: 'false'] as JSON)

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

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

		if(request.method == "GET") {
			def returnValue
			if (params.id) {
				render Account.get(params.id) as JSON
			}
			else {
				render view: "index", model: [accounts : Account.getAll()]
			}
		} else if(request.method == "POST") {

            if(params.fullname && params.emailaddress) {
                def account = Account.get(params.id) 
                account.fullName = params.fullname
                account.emailAddress = params.emailaddress
                try {
                    account.save(flush:true, failOnError:true)
                    render([success: 'true', data: [account: account]] as JSON)
                }
                catch(Exception e) {
                    println e.printStackTrace()
                }
            }
            else
                render([success: 'false'] as JSON)
		} 
    }

    def create() {
        if (params.username && params.password) {

            def account = new Account(username: params.username, password: params.password)
            try {
                account.save(failOnError: true)
            } catch(Exception e) {
                println e.printStackTrace()
            }
            redirect action: "login"
        }
    }

    def logout() {
        session.account = null
        session.token = null
        redirect action: "login"
    }
}
