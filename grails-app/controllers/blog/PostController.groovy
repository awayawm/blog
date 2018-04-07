package blog

class PostController {
    def index() {
        render(view: "index", model:[posts: [], tags:Tag.list()])
    }
    def addEdit() {}
    def deletePost() {}
}
