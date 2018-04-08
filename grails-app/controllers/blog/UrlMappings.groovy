package blog

class UrlMappings {

    static mappings = {

        group "/admin/tags", {
            "/addEdit"(controller:'tag', action: 'addEdit')
            "/deleteTag"(controller:'tag', action: 'deleteTag')
            "/"(controller: 'tag', action: 'index')
        }

        group "/admin/posts", {
            "/addEdit"(controller:'post', action: 'addEdit')
            "/deletePost"(controller:'post', action: 'deletePost')
            "/"(controller: 'post', action: 'index')
        }

        "500"(view:'/error')
        "404"(view:'/notFound')

        "/"(controller: 'index')

    }
}
