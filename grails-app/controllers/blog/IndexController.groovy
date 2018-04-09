package blog

import org.springframework.security.access.annotation.Secured

//TODO add back google analytics <g:insertGoogleAnalytics></g:insertGoogleAnalytics>

class IndexController {

    ConfigService configService = new ConfigService()

    @Secured('permitAll')
    def index() {
        def model = [:]
        if(Post.list().size() > 0){
            model.put("posts", Post.list())
        }
        if(Tag.list().size() > 0){
            model.put("tags", Tag.list())
        }
        model.put("title", configService.getConfig().title)
        model.put("tagline", configService.getConfig().tagline)
        render(view:"index", model: model)
    }

    @Secured('permitAll')
    def byTag(){
        Tag tag = Tag.findByShortUrl(params.shortUrl)
        def model = [:]
        def posts = []

        Post.list().each{
            if(tag in it.tags) { posts << it }
        }

        model.put("tag", tag)
        model.put("posts", posts)
        model.put("title", configService.getConfig().title)
        model.put("tagline", configService.getConfig().tagline)

        if(!tag) {
            flash.message = "Tag not found :("
            flash.title = "Opps!"
            flash.class = "alert alert-warning"
        } else {
            render(view:"byTag", model:model)
        }
    }

    @Secured('permitAll')
    def byPostShortUrl(){
        Post post = Post.findByShortUrl(params.shortUrl)
        def model = [:]
        model.put("title", configService.getConfig().title)
        model.put("tagline", configService.getConfig().tagline)
        model.put("post", post)
        if(!post) {
            flash.message = "Post not found :("
            flash.title = "Opps!"
            flash.class = "alert alert-warning"
        } else {
            render(view:"byPostShortUrl", model:model)
        }
    }

}
