package blog

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.security.InvalidKeyException

class Account {

    String fullName
    String username
    String password
    String emailAddress
    String role
    String resetToken
    Date dateCreated
    Date lastLoginTime

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
    String toString() {
        return "id ${id}\nusername ${username}\nrole ${role}\nlastLoginTime: ${lastLoginTime}"
    }

}
