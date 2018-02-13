package blog

class IndexController {

    def index() {
        [ posts: Post.findAll() ]
    }

}
