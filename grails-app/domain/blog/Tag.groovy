package blog

class Tag {
    String id
    String name
    String description

    @Override
    String toString() {
        "id ${id}\nname ${name}\ndescription ${description}"
    }
}
