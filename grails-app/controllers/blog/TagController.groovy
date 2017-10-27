package blog

import grails.converters.JSON

class TagController {

    AccountService accountService

    def index() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

    }

    def getAll() {
        return render([data: Tag.getAll()] as JSON)
    }

    def create() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

        def success = false

        if(params.name && params.description) {
            println params.name + " " + params.description
            def newTag = new Tag(name: params.name, description: params.description)
            newTag.save(flush:true)

            if(newTag != null)
                success = true

            return render([success: true] as JSON)
        }

    }

}
