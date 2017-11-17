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
        "id ${id}\nenabled ${enabled}\ntitle ${title}\ndatemodified ${datemodified}\ntags ${tags}\n"
    }
}
