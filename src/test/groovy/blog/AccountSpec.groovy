package blog

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AccountSpec extends Specification implements DomainUnitTest<Account>{
    def "Does create256ShaHash() create hashes correctly?"(){
        when:
        System.metaClass.'static'.getenv = { String name -> null }

        then:
        "CMh/k3rgwPNq+uw9kIKGN/77B1YVm4ETIYtzYnyesXE=" == new Account().create256ShaHash("blog")
    }

    def "Is plaintext password in new account hashed after calling save()"(){
        when:
        new Account(username: "blog", password: "blog", role: 'User', emailAddress: "blog").save()
        then:
        Account.findByUsername("blog").username == "blog"
    }
}
