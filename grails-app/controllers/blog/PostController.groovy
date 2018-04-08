package blog

class PostController {

    PostService postService = new PostService()

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
        println params

        Tag tag
        def tagIds = []
        Post post

        println params.tags.class

        log.info "params.tags.class.isArray() is ${params.tags.class.isArray()}"

        if(params.tags.class.isArray()){
            params.tags.each{
                tag = Tag.findByName(it)
                tagIds << tag.id
            }
        } else{
            tagIds << Tag.findByName(params.tags).id
        }

        if(params.id){

            if(postService.editPost(Long.valueOf(params.id), params.title, params.content, params.summary, params.shortUrl, params.enabled == "on" ? true : false, params.image, tagIds)){
                flash.title = "Nice!"
                flash.message = "Post successfully edited."
                flash.class = "alert alert-success"
            } else {
                flash.title = "Opps!"
                flash.message = "Post not added."
                flash.class = "alert alert-warning"
            }

        } else {
            if(postService.addPost(params.title, params.content, params.summary, params.shortUrl, params.enabled == "on" ? true : false, params.image, tagIds)){
                flash.title = "Nice!"
                flash.message = "Post successfully added."
                flash.class = "alert alert-success"
            } else {
                flash.title = "Opps!"
                flash.message = "Post not added."
                flash.class = "alert alert-warning"
            }
        }

        redirect url:'/admin/posts'
    }

    def deletePost() {
        Post post = Post.findById(params.id)
        if(post){
            post = post.delete(flush:true)
            flash.title = "Nice!"
            flash.message = "Post deleted successfully."
            flash.class = "alert alert-success"
        } else {
            flash.title = "On no!"
            flash.message = "Post deletion unsuccessful."
            flash.class = "alert alert-warning"
        }
        redirect url:'/admin/posts'
    }
}
