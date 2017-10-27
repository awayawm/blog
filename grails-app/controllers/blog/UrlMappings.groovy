package blog

class UrlMappings {

    static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }

        "/"(view:"/index")
        "/login"(controller: 'account', action: 'login')
        "/logout"(controller: 'account', action: 'logout')

        "/admin/account"(controller: 'account', action: 'index' )

        "/admin/tags"(controller:'tag', action: 'index')
        "/admin/tags/create"(controller:'tag', action: 'create')
        "/admin/tags/getAll"(controller:'tag', action: 'getAll')

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
