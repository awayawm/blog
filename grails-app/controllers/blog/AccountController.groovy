package blog

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.JWTCreator
import grails.converters.JSON
import groovyx.net.http.HTTPBuilder
import org.apache.log4j.Logger

import static groovyx.net.http.ContentType.URLENC

class AccountController {

    AccountService accountService
    ConfigService configService

    def login() {
        def account = new Account()
        def token
        Date expiresAt = null

        if(request.method == "POST") {
            ParamsChecker paramsChecker = new ParamsChecker(["username", "password"])

            if(paramsChecker.areRequirementsPresent()) {

                account = Account.findByUsername(params.username)

                if (account == null || !account.verifyPassword(params.password)) {
                    return render([success: false] as JSON)
                }

                def http = new HTTPBuilder( 'https://www.google.com' )
                def postBody = [secret: configService.getRecaptchaKey(), response: params.token]
                http.post( path: '/recaptcha/api/siteverify', body: postBody, requestContentType: URLENC ) { resp, json ->
                    if (json.success || !Config.findById(1).enableCaptcha) {
                        account.lastLoginTime = new Date()
                        session.account = account

                        if (params.remember_me)
                            expiresAt = new Date(new Date().getTime() + configService.getLongTokenTimer())
                        else
                            expiresAt = new Date(new Date().getTime() + configService.getShortTokenTimer())

                        try {
                            token = JWTCreator.init()
                                    .withExpiresAt(expiresAt)
                                    .sign(Algorithm.HMAC256(configService.getSecretKey()))

                            session.token = token
                            return render([success: true] as JSON)

                        } catch (Exception e) {
                            Logger.getLogger(this.class.name).error(e.printStackTrace())
                            return render([success: false] as JSON)
                        }
                    }
                }
                return render([success: false] as JSON)
            }
        } else if (request.method == "GET") {
            if(accountService.isTokenValid(session?.token))
                return redirect(controller: "account", action: "index")
        }
    }

    def remove() {
        def account
        if (params.id) {
            account = Account.get(params.id)
            try {
            account.delete(flush: true)
            } catch (Exception e) {
                Logger.getLogger(this.class.name).error(e.printStackTrace())
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
        } else if((request.method == "POST" && params.username && params.password && session?.account?.role == "Admin") || !Account.find({})) {
            println "admin ${admin}"
            def account = new Account(  username: params.username,
                                        password: params.password,
                                        role: admin == null ? 'Admin' : 'User',
                                        dateCreated: new Date())
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
