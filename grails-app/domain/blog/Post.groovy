package blog

class Post {
    String title
    String content
    String linkName
    Date dateCreated
    Date dateModified
    Boolean enabled = true

    static hasMany = [tags:Tag]

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
