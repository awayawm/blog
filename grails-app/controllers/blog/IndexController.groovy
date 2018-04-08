package blog
//TODO add back google analytics <g:insertGoogleAnalytics></g:insertGoogleAnalytics>

class IndexController {

    ConfigService configService = new ConfigService()

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

    def byTag(){

    }

}
