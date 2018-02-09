package blog

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class ConfigServiceSpec extends Specification implements ServiceUnitTest<ConfigService>{
    void "does getConfig return config"(){
        expect:
        service.getConfig() instanceof ConfigObject
    }
}
