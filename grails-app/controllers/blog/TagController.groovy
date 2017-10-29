// TODO validation
// TODO view foreign keys

package blog

import grails.converters.JSON

class TagController {

    AccountService accountService

    def index() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

    }

    def getall() {
        return render([data: Tag.getAll()] as JSON)
    }

    def getone() {
        if (params.id) {
            def tag = Tag.findById(params.id)
            if (tag)
                return render([success: true, data: [tag: tag]] as JSON)
            return render([success: false] as JSON)
        }
        return render([success: false] as JSON)
    }

    def create() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

        def success = false

        if(params.name && params.description) {

            if(params.id) {
                println Tag.findById(params.id)
                try {
                    def tag = Tag.findById(params.id)
                    tag.name = params.name
                    tag.dsecription = params.description
                    tag.save(flush: true)
                    return render([success: true] as JSON)
                } catch (Exception e) {
                    println e.printStackTrace()
                    return render([success: false] as JSON)
                }

            }

            def newTag = new Tag(name: params.name, description: params.description)
            try {
                newTag.save(flush: true)
            } catch (Exception e) {
                System.out e.printStackTrace()
            }
            if(newTag != null)
                success = true

            return render([success: success] as JSON)
        }

    }

    def remove() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

        if(params.id) {
            def tag = Tag.findById(params.id)
            if(tag) {
                tag.delete()
                return render([success: true] as JSON)
            } else {
                return render([success: false] as JSON)
            }
        }
    }

}
