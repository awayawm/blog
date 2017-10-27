package blog

class Tag {
    String name
    String description

    static hasMany = [posts: Post]

    static constraints = {
        name()
        description()
    }

    static mapping = {
        name index: true, indexAttributes: [unique: true, dropDups: true]
    }

    @Override
    public String toString() {
        return name
    }
}
