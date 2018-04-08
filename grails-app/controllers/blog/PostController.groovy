package blog

class PostController {
    def index() {
        def model = [:]
        Post post
        model.put("posts", Post.list())
        model.put("tags", Tag.list())
        if(params.id) {
            post = Post.findById(params.id)
            model.put("postId", post)
        }
        render(view: "index", model:model)
    }

    def addEdit() {

    }

    def deletePost() {

    }
}
