package blog

class Tag {

    String name
    String description
    Date dateCreated
    Date lastUpdated
    byte[] imageBytes
    String imageName
    String imageContentType


    static constraints = {
        name blank:false
        description blank:false
        imageBytes blank:false
        imageName blank:false
        imageContentType blank:false
    }
}
