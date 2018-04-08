package blog

import grails.testing.gorm.DataTest
import spock.lang.Specification

class PostSpec extends Specification implements DataTest{
    void setupSpec(){
        mockDomain Tag
        mockDomain Post
    }

    void setup(){

    }

    void "can post be created"(){

    }

    void "can post be deleted"(){

    }

}
