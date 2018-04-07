package blog

class Post {

    String title
    String content
    String summary
    String shortUrl
    Boolean enabled
    Date dateCreated
    Date lastUpdated
    byte[] imageBytes
    String imageName
    String imageContentType
    static hasMany = [tags:Tag]

    static constaints = {
        title blank:false
        content blank:false
        summary blank:false
        shortUrl blank:false
        enabled blank:false
    }
}
