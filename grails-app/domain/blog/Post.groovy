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
        imageBytes blank:false
        imageName blank:false
        imageContentType blank:false
    }

    static mapping = {
        imageBytes sqlType:'longblob'
        summary sqlType: 'varchar(10000)'
        content sqlType: 'varchar(50000)'
    }
}
