package blog

class TagController {

    AccountService accountService

    def index() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

    }

    def createTag() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

        if(params.name && params.description) {
            println params.name + " " + params.description
            new Tag(name: params.name, description: params.description).save(flush:true)
        }

    }

}
