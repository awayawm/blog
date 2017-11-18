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
    Account author
    Account lastmodifiedby

    static hasMany = [tags:Tag]

    static mapping = {
        id index:true
    }

    @Override
    String toString() {
        "id ${id}\nenabled ${enabled}\ntitle ${title}\ndatemodified ${datemodified}\ntags ${tags}\n" +
        "lastmodifiedby ${lastmodifiedby}\ndatemodified ${datemodified} datecreated ${datecreated}\nauthor ${author}"
    }
}
