package blog

class Post {
    String title
    String content
    String linkName
    Date dateCreated
    Date dateModified
    Boolean enabled = true
    Integer views = 0

    static hasMany = [tags:Tag]
    static belongsTo = Comment

    static constraints = {
        title()
        linkName(unique:true)
        content()
        enabled()
        dateModified()
        views()
    }

    @Override
    String toString() {
        return title
    }
}
