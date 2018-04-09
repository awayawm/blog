package blog

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

import javax.servlet.ServletContext

class ConfigServiceSpec extends Specification implements ServiceUnitTest<ConfigService>{

    void setupSpec(){}


    void cleanupSpec(){
        System.metaClass = null
    }

    void setup(){
        System.metaClass.static.getProperty = { String key ->
            System.setProperty("BLOG_CONFIG", ServletContext.getResource("/blog.config").file)
        }
    }
    def "can config be read from properties"(){
        when:
            println ServletContext.getResource("/blog.config").file
            println System.getProperty("BLOG_CONFIG")
            ConfigObject config
            config = service.getConfig()
        then:
            config.title == "My blog"
            config.tagline == "Life's a Journey!"
            config.author.size() == 2
    }

}
