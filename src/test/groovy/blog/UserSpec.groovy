package blog

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User>{

    void "can user be created?"(){
        when:
        new User(username:"admin", password:"admin").save(flush:true)
        then:
        User.list().size() == 1
    }

}
