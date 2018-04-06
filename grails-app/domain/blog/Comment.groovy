package blog

class Comment {

    String comment
    String author
    Boolean approved
    Date dateCreated
    Date lastUpdated

    static constraints = {
        author blank:false
        comment blank:false
        approved blank:false
    }
}
