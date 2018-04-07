package blog

class TagController {

    TagService tagService = new TagService()

    def index(){
        [tags:Tag.list()]
    }

    def addEdit(){
        Tag tag = tagService.editTag(params.name, params.description, params.image, params.id != null ? params.id : null)
        if(tag != null) {
            flash.message = "Added new tag successfully :)"
            flash.class = "alert alert-success"
        } else {
            flash.message = "Could not add new tag :("
            flash.class = "alert alert-warning"
        }
        redirect url: '/admin/tags'
    }

    def delete(){

    }
}
