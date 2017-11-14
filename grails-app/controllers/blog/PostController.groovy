package blog

import grails.converters.JSON

class PostController {

    AccountService accountService

    def index() {

        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def tags = Tag.findAll()
        if(tags) {
            return [tags: tags]
        }

    }

    def getpost() {

        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def requiredParams = ["id"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        if(paramsChecker.areRequirementsPresent()) {

            def post = Post.findById(params?.id)
            if (post) {
                return render([success: true, data: [post: post]] as JSON)
            }
        }
        render([success:false] as JSON)
    }

    def getposts() {
        def posts = Post.findAll()
        if(posts) {
            return render([success:true, data: [posts: posts]] as JSON)
        }
        render([success:false] as JSON)
    }

    def deletepost() {
        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def requiredParams = ["id"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        if(paramsChecker.areRequirementsPresent()) {
            def post = Post.findById(params.id)
            if(post) {
                post.delete()
//                println "\n${post}"
                if(post) {
                    return render([success: 'true', data: [post: post]] as JSON)
                }
            }
        }
        render([success: 'false'] as JSON)
    }

    def submit() {
        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def requiredParams = ["title", "link", "summary", "content", "enabled", "tags"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        if(paramsChecker.areRequirementsPresent()) {

            if(params?.id) {
                println "params: ${params.list('tags[]')}"

                def post = Post.findById(params?.id)
                if(!post) {
                    return render([success:false] as JSON)
                }
                post.title = params?.title
                post.summary = params?.summary
                post.link = params?.link
                post.content = params?.content
                post.enabled = params?.enabled

//                params?.tags.each {
//                    println ${it}
//                }
//                post.addToTags(params?.tags)

                post.save(flush:true)
                return render([success:true, data:[post:post]] as JSON)

            }

            def post = new Post(
                    title: params.title,
                    summary: params.summary,
                    link: params.link,
                    content: params.content,
                    enabled: params.enabled
            )
            post.insert()
            if(post)
                return render([success: 'true', data: [post: post]] as JSON)
        }

        return render([success: 'false'] as JSON)
    }

}
