package blog

class Account {

    String fullName
    String username
    String password
    String emailAddress
    String role
    Date dateCreated

    static constraints = {
        username unique:true
        password blank:false
        fullName blank:true
        emailAddress blank:true
        role blank:true
    }
}
