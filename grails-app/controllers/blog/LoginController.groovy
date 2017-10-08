package blog

class LoginController {

    def index() { }

    def authenticate() { 
        println params
        render "authenticating"
    }

}
