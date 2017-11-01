package blog

class UrlMappings {

    static mappings = {

        "/"(view:"/index")
        "/login"(controller: 'account', action: 'login')
        "/logout"(controller: 'account', action: 'logout')

        group "/admin/account", {
            "/"(controller: 'account', action: 'index')
            "/create"(controller: 'account', action: 'create')
            "/remove"(controller: 'account', action: 'remove')
        }

        group "/admin/tags", {
            "/"(controller: 'tag', action: 'index')
            "/create"(controller: 'tag', action: 'create')
            "/getall"(controller: 'tag', action: 'getall')
            "/getone/$id"(controller: 'tag', action: 'getone')
            "/remove"(controller: 'tag', action: 'remove')
        }

        group "/admin/posts", {
            "/"(controller: "post", action: "index")
            "/edit"(controller: "post", action: "edit")
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
