package blog

class Tag {
    String name
    String description

//    static hasMany = [posts:Post]

    static constraints = {}

    @Override
    String toString() {
        "name: ${name}\ndescription: ${description}"
    }
}
