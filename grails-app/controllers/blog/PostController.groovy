package blog

class PostController {
    def index() {
        render(view: "index", model:[posts: Post.list(), tags:Tag.list()])
    }
    def addEdit() {}
    def deletePost() {}
}
