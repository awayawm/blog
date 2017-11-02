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

        List<String> requiredParams = new ArrayList<>("title", "link", "summary", "content", "enabled")
//        ParamsChecker paramsChecker = new ParamsChecker()

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index") as JSON



        render([success: 'false'] as JSON)
    }

}
