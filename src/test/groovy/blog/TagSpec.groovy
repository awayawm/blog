package blog

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TagSpec extends Specification implements DomainUnitTest<Tag> {

    File image1 = new File(this.class.classLoader.getResource("../../resources/test/images/Music-Note.jpg").toURI())
    File image2 = new File(this.class.classLoader.getResource("../../resources/test/images/staunton-chess-set-1.jpg").toURI())

    void setupSpec(){
    }

    void setup(){
        Tag.findAll().each { it -> it.delete() }
    }

    def "can tags be saved"(){
        when:
            new Tag(name: "music", description: "music is the best outlit", imageBytes: image1.bytes, imageName: image1.name, imageContentType: "image/jpg").save(failOnError:true)
            new Tag(name: "chess programming", description: "chess programming is interesting", imageBytes: image2.bytes, imageName: image2.name, imageContentType: "image/jpg").save(failOnError:true)

        then:
            Tag.list().size() == 2
    }

    def "can tag be removed"(){
        when:
        Tag tag = new Tag(name: "music", description: "music is the best outlit", imageBytes: image1.bytes, imageName: image1.name, imageContentType: "image/jpg").save(failOnError:true)
        Tag.findById(tag.id).delete(flush:true,failOnError: true)

        then:
        Tag.list().size() == 0
    }

}
