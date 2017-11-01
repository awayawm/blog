package blog

import grails.converters.JSON

class PostController {
    AccountService accountService

    def index() {

        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

    }

    def edit() {

        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        println params

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index") as JSON

        render([success: 'false'] as JSON)
    }

}
