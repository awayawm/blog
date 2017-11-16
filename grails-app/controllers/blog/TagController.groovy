// TODO validation
// TODO view foreign keys

package blog

import grails.converters.JSON

class TagController {

    AccountService accountService

    def index() {

        if(!accountService.isTokenValid(session.token))
            return redirect(controller: "account", action: "login")

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

    }

    def getall() {
        def tags = Tag.findAll()
        if(tags) {
            return render([success:true, data:[tags:tags]] as JSON)
        }
        render([success:false] as JSON)
    }

    def gettag() {
        def requiredParams = ["id"]
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(paramsChecker.areRequirementsPresent()) {

            def tag = Tag.findById(params.id)
            if (tag) {
                return render([success: true, data: [tag: tag]] as JSON)
            }

        }
        return render([success: false] as JSON)
    }

    def submit() {

        if(!accountService.isTokenValid(session.token))
            return redirect(controller: "account", action: "login")

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        def requiredParams = ["name", "description"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(paramsChecker.areRequirementsPresent()) {

            if (params.name && params.description) {
                if (params?.id) {
                    println params
                    def tag = Tag.findById(params.id)
                    tag.name = params.name
                    tag.description = params.description
                    tag.save(flush: true)
                    return render([success: true, data: [tag:tag]] as JSON)
                }

                def tag = new Tag(name: params.name,
                        description: params.description
                )
                tag.insert()
                return render([success: true, data: [tag:tag]] as JSON)

            }
        }
        return render([success: false] as JSON)

    }

    def remove() {

        if(!accountService.isTokenValid(session.token))
            redirect controller: "account", action: "login"

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        def requiredParams = ["id"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(paramsChecker.areRequirementsPresent()) {
            def tag = Tag.findById(params.id)
            if (tag) {
                tag.delete()
                return render([success: true] as JSON)
            }
        }
        return render([success: false] as JSON)
    }

}
