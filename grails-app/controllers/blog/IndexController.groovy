package blog

import org.springframework.security.access.annotation.Secured

//TODO add back google analytics <g:insertGoogleAnalytics></g:insertGoogleAnalytics>



class IndexController {

    ConfigService configService = new ConfigService()


    def makeModel(def model){
        model.put("title", configService.getConfig().title)
        model.put("tagline", configService.getConfig().tagline)
        model.put("twitter", configService.getConfig().author.twitter)
        model.put("linkedin", configService.getConfig().author.linkedin)
        model.put("github", configService.getConfig().author.github)
        model.put("htmlTitle", configService.getConfig().htmlTitle)
        model.put("favicon", configService.getConfig().favicon)
        model.put("trackingId", configService.getConfig().trackingId)
        model.put("tags", Tag.list())
        model
    }

    @Secured('permitAll')
    def index() {
        def model = [:]
        model.put("posts", Post.findAll { enabled == true })
        model = makeModel(model)

        if (model.posts.size() == 0){
            flash.message = "No posts enabled, come back later :("
            flash.title = "Opps!"
            flash.class = "alert alert-warning"
        }

        render(view:"index", model: model)
    }

    @Secured('permitAll')
    def byTag(){
        Tag tag = Tag.findByShortUrl(params.shortUrl)
        def model = [:]
        def posts = []

        Post.findAll { enabled == true }.each{
            if(tag in it.tags) { posts << it }
        }

        model.put("tag", tag)
        model.put("posts", posts)
        model = makeModel(model)

        if(model.posts.size() == 0) {
            flash.message = "No posts enabled with this tag :("
            flash.title = "Opps!"
            flash.class = "alert alert-warning"
        }
        render(view:"byTag", model:model)
    }

    @Secured('permitAll')
    def byPostShortUrl(){
        Post post = Post.findByShortUrl(params.shortUrl)
        if (!post.enabled){
            post = null
        }
        def model = [:]
        model = makeModel(model)
        model.put("post", post)

        if(!post) {
            flash.message = "Post not found :("
            flash.title = "Opps!"
            flash.class = "alert alert-warning"
        }
        render(view:"byPostShortUrl", model:model)
    }

    @Secured(value=["hasRole('ROLE_ADMIN')"])
    def byPostShortUrlAdmin(){
        Post post = Post.findByShortUrl(params.shortUrl)
        def model = [:]
        model = makeModel(model)
        model.put("post", post)

        if(!post) {
            flash.message = "Post not found :("
            flash.title = "Opps!"
            flash.class = "alert alert-warning"
        }
        render(view:"byPostShortUrl", model:model)
    }

}
