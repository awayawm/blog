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

        def requiredParams = ["id"]
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        if(paramsChecker.areRequirementsPresent()) {
            def post = Post.findById(params.id)
            if(post) {
                post.delete()
                return render([success: 'true', data: [post: post]] as JSON)
            }
        }
        render([success: 'false'] as JSON)
    }

    def copypost() {
        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def requiredParams = ["id"]
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        if(paramsChecker.areRequirementsPresent()) {

            if (params?.id) {
                def post = Post.findById(params?.id)
                post.id = null
                post.datemodified = new Date()
                post.discard()
                post.save(flush:true)
                return render([success:true, data:[post:post]] as JSON)
            }
        }
        return render([success:false] as JSON)
    }

    def submit() {
        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def requiredParams = ["title", "link", "summary", "content", "enabled", "tags[]"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index")

        if(paramsChecker.areRequirementsPresent()) {

            if(params?.id) {

//                println new ParamList("tags").ReturnParamList() as List

                def post = Post.findById(params?.id)
                if(!post) {
                    return render([success:false] as JSON)
                }

                post.title = params?.title
                post.summary = params?.summary
                post.link = params?.link
                post.content = params?.content
                post.enabled = params?.enabled == "true" ? true : false
                post.datemodified = new Date()
                post.lastmodifiedby = session.account

                if(post.tags != null) {
                    post.tags.clear()
                }

                new ParamList("tags").ReturnParamList().each { tag ->
                    post.addToTags(Tag.findById(tag))
                }
                post.save(flush:true)
                return render([success:true, data:[post:post]] as JSON)

            }

            def post = new Post(
                    title: params.title,
                    content: params.content,
                    summary: params.summary,
                    link: params.link,
                    enabled: params.enabled,
                    datecreated: new Date(),
                    datemodified: new Date(),
                    author: session.account,
                    lastmodifiedby: session.account
            )

            new ParamList("tags").ReturnParamList().each { tag ->
                post.addToTags(Tag.findById(tag))
            }

            post.insert()
            if(post) {
                return render([success: 'true', data: [post: post]] as JSON)
            }
        }

        return render([success: 'false'] as JSON)
    }

}
