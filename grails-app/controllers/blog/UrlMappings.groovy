package blog

class UrlMappings {

    static mappings = {

        group "/admin/tag", {
            "/"(controller: 'tag', action: 'index')
        }

        "500"(view:'/error')
        "404"(view:'/notFound')

        "/"(controller: 'index')

    }
}
