package blog

import org.apache.log4j.Logger
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

    static constraints = {
        fullName nullable: true
        resetToken nullable: true
        lastLoginTime nullable: true
        dateCreated defaultValue: (new Date())
    }

    def create256ShaHash(String password) {

        if(!new ConfigService().getSecretKey()) {
            Logger.getLogger(this.getClass().name).info("Environmental variable SECRET_KEY is not set.")
        }

		try {
			Mac mac = Mac.getInstance("HmacSHA256")
			SecretKeySpec secretKeySpec = new SecretKeySpec(new ConfigService().getSecretKey().getBytes(), "HmacSHA256")
			mac.init(secretKeySpec)
			byte[] digest = mac.doFinal(password.getBytes())
			return digest.encodeBase64().toString()
		} catch (InvalidKeyException e) {
            Logger.getLogger(this.getClass().name).info("Invalid key exception while converting to HMac SHA256")
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
        return "id ${id}\nusername ${username}\npassword ${password}\nrole ${role}\nlastLoginTime: ${lastLoginTime}"
    }

}
