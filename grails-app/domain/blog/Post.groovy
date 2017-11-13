package blog

class Post {
    String title
    String content
    String summary
    String link
    Boolean enabled
//    Integer views

//    static hasMany = [tags:Tag]
//    static belongsTo = Comment

    static constraints = { }

    @Override
    String toString() {
        "id ${id}\ntitle ${title}\nsummary ${summary}"
    }
}
