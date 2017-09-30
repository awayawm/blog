package blog

class Comment {

    String comment
    String author
    Boolean approved
    Date dateCreated
    Post associatedPost;
    Comment associatedComment;

    static belongsTo = [associatedPost:Post, associatedComment:Comment]

    static constraints = {
        author()
        comment()
        approved()
        associatedPost()
    }
}
