package blog

class Tag {
    String name
    String description

    static hasMany = [posts: Post]
    static belongsTo = Post

    static constraints = {
        name()
        description()
    }

    @Override
    public String toString() {
        return name
    }
}
