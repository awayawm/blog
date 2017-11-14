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
            "/submit"(controller: 'tag', action: 'submit')
            "/getall"(controller: 'tag', action: 'getall')
            "/gettag/$id"(controller: 'tag', action: 'gettag')
            "/remove"(controller: 'tag', action: 'remove')
        }

        group "/admin/posts", {
            "/"(controller: "post", action: "index")
            "/submit"(controller: "post", action: "submit")
            "/getposts"(controller: "post", action: "getposts")
            "/deletepost"(controller: "post", action: "deletepost")
            "/getpost/$id"(controller: "post", action: "getpost")
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
