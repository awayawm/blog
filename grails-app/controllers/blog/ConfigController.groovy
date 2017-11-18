package blog

import grails.converters.JSON

class ConfigController {
    AccountService accountService

    def index() {
        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

    if(session?.account?.role != 'Admin')
        return redirect(controller: "post", view: "index")

    }

    def submit() {
        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def requiredParams = ["title", "tagline", "favicon", "shorttokentimer", "longtokentimer", "enablecaptcha"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        if(paramsChecker.areRequirementsPresent()) {

            def config = Config.find({_id:1})
            if (config == null) {
                return render(false) as JSON
            } else {
                config.title = params.title
                config.tagline = params.tagline
                config.favicon = params.favicon
                config.enableCaptcha = params.enablecaptcha == 'true' ? true : false
                config.shortTokenTimer = Integer.parseInt(params.shorttokentimer)
                config.longTokenTimer = Integer.parseInt(params.longtokentimer)
                config.save(flush:true)
                return render(true) as JSON
            }
        }
    }

    def get() {
        if (!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        if (session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        def config = Config.findById(1)
        render config as JSON
    }

}
