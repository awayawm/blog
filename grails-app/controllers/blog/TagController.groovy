package blog

class TagController {

    TagService tagService = new TagService()

    def index(){
        [tags:Tag.list()]
    }

    def addEdit(){
        Tag tag = tagService.editTag(params.name, params.description, params.image, params.id != null ? params.id : null)
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

    def delete(){
        Tag tag = tagService.deleteTag(params.id)
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
