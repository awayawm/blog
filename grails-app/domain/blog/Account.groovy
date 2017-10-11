package blog

class Account {

    String fullName
    String username
    String password
    String emailAddress
    String role
    Date dateCreated

    static constraints = {
        username unique: true
        password blank: false
        fullName nullable:true, blank:true
        emailAddress nullable:true, blank:true
        role nullable:true, blank:true, inList: ['Admin', 'Member']
    }

    @Override
    public String toString() {
        return id + ": " + username;
    }
}
