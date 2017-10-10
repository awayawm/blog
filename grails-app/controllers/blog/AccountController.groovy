package blog

class AccountController {

    def index() { 
        [accounts: Account.getAll()] 
    }

    def create() {
        if (params.username && params.password) {
            println "creating account"

            def newAccount = new Account(username: params.username, password: params.password)
            try {
                newAccount.save(failOnError: true)
            } catch(Exception e) {
                println e
            }
            redirect(action: 'index')
        }
    }
}
