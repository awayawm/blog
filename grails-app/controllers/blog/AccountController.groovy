package blog

import grails.converters.JSON

class AccountController {

    def login() {
        if(request.method == "POST") {
            if(params.username && params.password) {
                try {
                def account = Account.findByUsername(params.username) 
                } catch (Exception e) {
                    println e.printStackTrace()
                    render([success: 'false'] as JSON)
                }

                if(account.verifyPassword(params.password))
                    render([success: 'true'] as JSON)
                else
                    render([success: 'false'] as JSON)
            }
            else
                render([success: 'false'] as JSON)
        }
    }

    def remove() {
        if (params.id) {
            def account = Account.get(params.id)
            try {
            account.delete(flush: true)
            } catch (Exception e) {
                println e.printStackTrace()
            }
            render([success: 'true', data: [ status: 'delete function called' ]] as JSON)
        }
    }

    def index() { 
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
                    println "error: " + e.printStackTrace()
                }
            }
            else
                render([success: 'false'] as JSON)
		} 
    }

    def create() {
        if (params.username && params.password) {

            def newAccount = new Account(username: params.username, password: params.password)
            try {
                newAccount.save(failOnError: true)
            } catch(Exception e) {
                println e
            }
            redirect action: "index"
        }
    }

}
