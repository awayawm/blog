package blog

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class AccountControllerSpec extends Specification implements ControllerUnitTest<AccountController>, DataTest {

    private createConfig(){
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
    }

        void setupSpec(){
            mockDomain Account
            mockDomain Config
        }

    void "does calling login() with valid credentials return success true?"() {
        when:
        createConfig()
        controller.configService = new ConfigService()

        new Account(username: "jack",
                password: "black",
                role: 'User',
                emailAddress: 'jack@black.edu').save()

        params.username = "jack"
        params.password = "black"
        request.method = "POST"
        controller.login()

        then:
        response.json.success == true
    }

    void "does calling login() with invalid login credentials return false?"() {
        when:
        createConfig()
        controller.configService = new ConfigService()

        new Account(username: "jack",
                password: "black",
                role: 'User',
                dateCreated: new Date()).save()

        request.method = "POST"
        params.username = "jack"
        params.password = "white"
        controller.login()

        then:
        response.json.success == false
    }
}
