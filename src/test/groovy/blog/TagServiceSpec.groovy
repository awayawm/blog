package blog

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.grails.plugins.testing.GrailsMockMultipartFile
import spock.lang.Specification

class TagServiceSpec extends Specification implements ServiceUnitTest<TagService>, DataTest {

    void setup(){
        mockDomain Tag
    }

    File image1 = new File(this.class.classLoader.getResource("../../resources/test/images/Music-Note.jpg").toURI())
    File image2 = new File(this.class.classLoader.getResource("../../resources/test/images/staunton-chess-set-1.jpg").toURI())
    File image3 = new File(this.class.classLoader.getResource("../../resources/test/images/ThinkstockPhotos-494037394.jpg").toURI())

    GrailsMockMultipartFile multipartFile1 = new GrailsMockMultipartFile("multipartFile1", "multipartFile1", "image/jpeg", image1.bytes)
    GrailsMockMultipartFile multipartFile2 = new GrailsMockMultipartFile("multipartFile2", "multipartFile2", "image/jpeg", image2.bytes)
    GrailsMockMultipartFile multipartFile3 = new GrailsMockMultipartFile("multipartFile3", "multipartFile3", "image/jpeg", image3.bytes)

    void "can a tag be added"(){
        when:
            service.editTag("music","music", "music education is important", multipartFile1)
            service.editTag("chess-programming","chess programming", "chess programming is interesting", multipartFile2)
        then:
            Tag.list().size() == 2
    }

    void "can a tag be edited"(){
        when:
            Tag tag1 = service.editTag("cool-music","music", "music education is important", multipartFile1)
            Tag updatedTag = service.editTag("free oranges","oranges", "oranges have a great aroma and taste great too", multipartFile2, tag1.id)
        then:
            def result = Tag.findById(tag1.id)
            result.name == "oranges"
            result.shortUrl == "free oranges"
            result.description == "oranges have a great aroma and taste great too"
            result.imageName == multipartFile2.name
            result.imageContentType == multipartFile2.contentType
            result.imageBytes == multipartFile2.bytes


    }

    void "can a tag be deleted"(){
        when:
        Tag tag1 = service.editTag("nice","music", "music education is important", multipartFile1)
        service.deleteTag(tag1.id)

        then:
        Tag.list().size() == 0
    }

}
