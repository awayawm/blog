package blog

class UrlMappings {

    static mappings = {

        "/"(view:"/index")
        "/login"(controller: 'account', action: 'login')
        "/logout"(controller: 'account', action: 'logout')

        group "/admin/account", {
            "/"(controller: 'account', action: 'index')
            "/create"(controller: 'account', action: 'create')
        }

        "/admin/tags"(controller: 'tag', action: 'index')
        "/admin/tags/create"(controller: 'tag', action: 'create')
        "/admin/tags/getall"(controller: 'tag', action: 'getall')
        "/admin/tags/getone/$id"(controller: 'tag', action: 'getone')
        "/admin/tags/remove"(controller: 'tag', action: 'remove')

        group "/admin/posts", {
            "/"(controller: "post", action: "index")
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
