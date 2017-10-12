package blog

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/login"(controller: 'account', action: 'login')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
