package blog

class IndexController {
    def index() {
        def posts = Post.findAll()
        [posts:posts]
    }

    def getPostByLink() {
        def requiredParams = ["link"] as ArrayList
        ParamsChecker paramsChecker = new ParamsChecker(requiredParams)

        if(paramsChecker.areRequirementsPresent()) {
            return render(view:"postByLink", model: [post:Post.findByLink(params?.link)])
        }
        redirect action:"/"
    }
}
