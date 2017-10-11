package blog

import grails.converters.JSON

class AccountController {

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
