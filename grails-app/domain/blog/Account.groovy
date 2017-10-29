package blog

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.InvalidKeyException

class Account {

    String fullName
    String username
    String password
    String emailAddress
    String role = 'User'
    String resetToken
    Date dateCreated

    static constraints = {
        username unique: true
        password blank: false
        fullName nullable:true, blank:true
        emailAddress nullable:true, blank:true
        role nullable:true, blank:true, inList: ['Admin', 'User']
        resetToken nullable: true, blank:true
    }

    def create256ShaHash(String password) {
		try {
			Mac mac = Mac.getInstance("HmacSHA256")
			SecretKeySpec secretKeySpec = new SecretKeySpec(System.getenv("SECRET_KEY").getBytes(), "HmacSHA256")
			mac.init(secretKeySpec)
			byte[] digest = mac.doFinal(password.getBytes())
			return digest.encodeBase64().toString()
		} catch (InvalidKeyException e) {
			throw new RuntimeException("Invalid key exception while converting to HMac SHA256")
		}
    }

    def verifyPassword(String password) {
        create256ShaHash(password) == this.password
    }

    def beforeInsert() {
        password = create256ShaHash(password)
    }

    def beforeUpdate() {
        password = create256ShaHash(password)
    }

    @Override
    public String toString() {
        return "id: " + id + ", username: " + username;
    }

}
