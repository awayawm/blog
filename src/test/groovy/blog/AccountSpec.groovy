package blog

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AccountSpec extends Specification implements DomainUnitTest<Account>{
    def "Does create256ShaHash() create hashes correctly?"(){
        expect:
        "g0V2W1iY0z+Xj+SFIgQcAuXwCBUPn6SwjVQoKPBiYdk=" == new Account().create256ShaHash("blog")
    }
}
