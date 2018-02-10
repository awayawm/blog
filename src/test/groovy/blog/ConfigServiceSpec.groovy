package blog

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class ConfigServiceSpec extends Specification implements ServiceUnitTest<ConfigService>, DataTest{
    void setupSpec(){
        mockDomain Config
    }

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

    void "get Config domain object"(){
        when:
        createConfig()

        then:
        service.getConfig() instanceof Config
    }

    void "retreive short token timer"(){
        when:
        createConfig()

        then:
        service.getShortTokenTimer() instanceof Integer
        service.getShortTokenTimer() == 20 * 60 * 1000
    }

    void "retrieve long token timer"(){
        when:
        createConfig()

        then:
        service.getLongTokenTimer() instanceof Integer
        service.getLongTokenTimer() == 60 * 60 * 1000
    }

    void "can get recaptcha code"(){
        when:
        createConfig()

        then:
        service.getRecaptchaKey() instanceof String
        service.getRecaptchaKey() == "none"
    }

    void "does getSecretKey() return a string when null (not set on server)"(){
        when:
        System.metaClass.'static'.getenv = { String name -> name }

        then:
        service.getSecretKey() != null
    }

}
