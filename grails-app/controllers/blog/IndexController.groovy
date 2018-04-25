package blog

import org.springframework.security.access.annotation.Secured

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
        model.put("tags", Tag.list().take(configService.getConfig().number_of_tags_in_navbar).sort { Math.random() })
        model
    }

    def processTags(def posts){
        def postsWithEnabledTags = []
        posts.each { post ->
            def tags = []
            post.tags.each { tag ->
                if(tag.enabled) tags << tag
            }
            post.tags = []
            post.tags = tags
            if (post.tags.size() != 0) postsWithEnabledTags << post
        }
        postsWithEnabledTags
    }

    @Secured('permitAll')
    def index() {
        def model = [:]
        def enablePosts = Post.findAll { enabled == true }

        model.put("posts", processTags(enablePosts).sort{a,b-> b.lastUpdated<=>a.lastUpdated})
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
        model.put("posts", processTags(posts).sort{a,b-> b.lastUpdated<=>a.lastUpdated})
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
        model.put("post", processTags(post)[0])

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
