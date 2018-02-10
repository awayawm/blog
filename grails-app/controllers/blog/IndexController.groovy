package blog

class IndexController {

    def index() {
        def posts = Post.findAll()
        [posts:posts]
    }

}
