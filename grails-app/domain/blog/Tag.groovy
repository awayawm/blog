package blog

class Tag {

    String name
    String description
    Date dateCreated
    Date lastUpdated
    byte[] imageBytes
    String imageName
    String imageFiletype

    static constraints = {
        name blank:false
        description blank:false
        image blank:false
    }
}
