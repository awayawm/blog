package blog

class Comment {

    String comment
    String author
    Boolean approved
    Date dateCreated

    static belongsTo = Post

    static constraints = {
        author()
        comment()
        approved()
    }
}
