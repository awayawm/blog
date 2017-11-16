package blog

class Post {
    String id
    String title
    String content
    String summary
    String link
    Boolean enabled
    Date datecreated
    Date datemodified
    static hasMany = [tags:Tag]

    static mapping = {
        id index:true
    }

    @Override
    String toString() {
        "id ${id}\ntitle ${title}\nsummary ${summary}\ntags ${tags}\n"
    }
}
