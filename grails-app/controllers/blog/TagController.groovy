package blog

class TagController {

    TagService tagService = new TagService()

    def index(){
        Tag foundTag
        def model = [:]
        model.put('tags', Tag.list())
        if(params.id){
            foundTag = Tag.findById(params.id)
            model.put('name', foundTag.name)
            model.put('id', foundTag.id)
            model.put('description', foundTag.description)
            model.put('shortUrl', foundTag.shortUrl)
            model.put('imageBytes', foundTag.imageBytes)
            model.put('imageName', foundTag.imageName)
            model.put('imageContentType', foundTag.imageContentType)
        }

        return render(view: 'index', model: model)
    }

    def addEdit(){
        println params
        Tag tag = tagService.editTag(params.shortUrl, params.name, params.description, params.image, params.id != null ? Long.valueOf(params.id) : null)
        if(tag != null) {
            flash.title = "Good news :)"
            flash.message = "Added new tag successfully."
            flash.class = "alert alert-success"
        } else {
            flash.title = "Oh no :("
            flash.message = "Could not add new tag."
            flash.class = "alert alert-warning"
        }
        redirect url: '/admin/tags'
    }

    def deleteTag(){
        Tag tag = tagService.deleteTag(params.id instanceof String ? Long.valueOf(params.id) : params.id)
        if(tag){
            flash.title = "Oh no :("
            flash.message = "Unable to delete the tag for some reason."
            flash.class = "alert alert-warning"
        } else {
            flash.title = "Nice!"
            flash.message = "The tag is removed now."
            flash.class = "alert alert-success"
        }
        redirect url: '/admin/tags'
    }
}
