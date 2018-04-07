package blog

class UrlMappings {

    static mappings = {

        group "/admin/tags", {
            "/"(controller: 'tag', action: 'index')
        }

        "500"(view:'/error')
        "404"(view:'/notFound')

        "/"(controller: 'index')

    }
}
