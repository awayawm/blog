package blog

import grails.converters.JSON

class PostController {

    AccountService accountService

    def index() {

        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

    }

    def getposts() {
        def posts = Post.findAll()
        if(posts) {
            return render([success:true, data: [posts: posts]] as JSON)
        }
        render([success:false] as JSON)
    }

    def edit() {

        if(!accountService.isTokenValid(session?.token))
            return redirect(controller: "account", action: "login")

        def requiredParams = ["title", "link", "summary", "content", "enabled"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(session?.account?.role != 'Admin')
            return redirect(controller: "post", view: "index") as JSON

        if(paramsChecker.areRequirementsPresent()) {
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
