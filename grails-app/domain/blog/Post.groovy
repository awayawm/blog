package blog

class Post {
    String title
    String content
    String linkName
    Date dateCreated
    Date dateModified
    Boolean enabled = true
    Integer views

    static hasMany = [tags:Tag]
    static belongsTo = [Tag, Comment]

    static constraints = {
        title()
        linkName(unique:true)
        content()
        enabled()
        dateModified()
    }

    @Override
    String toString() {
        return title
    }
}
