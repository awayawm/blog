package blog

import grails.gorm.transactions.Transactional
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

@Transactional
class AccountService {

    def isTokenValid(token) {
        if(token) {
            DecodedJWT decoded;
            try {
                decoded = JWTVerifier.init(Algorithm.HMAC256(System.getenv("SECRET_KEY"))).build().verify(token)
                //println decoded.getToken()
            }
            catch(Exception e)
            {
                println e.printStackTrace()
            }
            return (decoded != null)
        }
        else {
            return false
        }
    }

}
