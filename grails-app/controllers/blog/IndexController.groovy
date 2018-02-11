package blog

class IndexController {

    def index() {
        println Post.findAll()[0]
        [ posts: Post.findAll() ]
    }

}
