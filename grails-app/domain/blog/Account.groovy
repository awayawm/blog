package blog

class Account {

    String fullName
    String username
    String password
    String emailAddress
    String role
    Date dateCreated

    static constraints = {
        username()
        password()
        fullName()
        emailAddress()
        role()
    }
}
