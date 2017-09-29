package blog

class Topic {

    String name
    String description

    static constraints = {
        name()
        description()
    }

    @Override
    String toString() {
        return name
    }
}
