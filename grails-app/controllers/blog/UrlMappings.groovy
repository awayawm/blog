package blog

class UrlMappings {

    static mappings = {

        "/"(controller: 'index')
        "/$link"(controller: 'index', action: 'getPostByLink')
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
            "/copypost/$id"(controller: "post", action: "copypost")
            "/submit"(controller: "post", action: "submit")
            "/getposts"(controller: "post", action: "getposts")
            "/deletepost"(controller: "post", action: "deletepost")
            "/getpost/$id"(controller: "post", action: "getpost")
        }

        group "/admin/config", {
            "/"(controller: "config", action: "index")
            "/submit"(controller: "config", action: "submit")
            "/get"(controller: "config", action: "get")
            "/getenablecaptcha"(controller: "config", action: "getEnableCaptcha")
        }

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
