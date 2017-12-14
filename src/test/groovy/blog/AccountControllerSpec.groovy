package blog

import grails.testing.gorm.DataTest
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AccountControllerSpec extends Specification implements ControllerUnitTest<AccountController>, DataTest {

        void setupSpec(){
            mockDomain Account
            mockDomain Config
        }

    void "does login with valid username and password return true?"() {
        when:
        new Config(
                id: 1,
                shortTokenTimer: 20 * 60 * 1000,
                longTokenTimer: 60 * 60 * 1000,
                siteTitle: "My blog",
                title: "Welcome to My blog",
                tagline: "Life's a journey!",
                favicon: "none.ico",
                enableCaptcha: false,
                recaptchaKey: 'none',
                analyticsKey: 'none'
        ).save()

        new Account(username: "jack",
                password: "black",
                role: 'User',
                dateCreated: new Date()).save()

        params.username = "jack"
        params.password = "black"
        request.method = "POST"
        controller.login()

        then:
        response.json.success == true

    }
}
