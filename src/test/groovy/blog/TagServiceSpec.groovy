package blog

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.grails.plugins.testing.GrailsMockMultipartFile
import org.h2.jdbc.JdbcSQLException
import spock.lang.Specification

class TagServiceSpec extends Specification implements ServiceUnitTest<TagService>, DataTest {

    void setupSpec(){
        mockDomain Tag
        mockDomain Post
    }

    void cleanup(){
        Tag.metaClass = null
    }

    File image1 = new File(this.class.classLoader.getResource("../../resources/test/images/Music-Note.jpg").toURI())
    File image2 = new File(this.class.classLoader.getResource("../../resources/test/images/staunton-chess-set-1.jpg").toURI())
    File image3 = new File(this.class.classLoader.getResource("../../resources/test/images/ThinkstockPhotos-494037394.jpg").toURI())

    GrailsMockMultipartFile multipartFile1 = new GrailsMockMultipartFile("multipartFile1", "multipartFile1", "image/jpeg", image1.bytes)
    GrailsMockMultipartFile multipartFile2 = new GrailsMockMultipartFile("multipartFile2", "multipartFile2", "image/jpeg", image2.bytes)
    GrailsMockMultipartFile multipartFile3 = new GrailsMockMultipartFile("multipartFile3", "multipartFile3", "image/jpeg", image3.bytes)

    void "can a tag be added"(){
        when:
            service.editTag("on", "music","music", "music education is important", multipartFile1)
            service.editTag("", "chess-programming","chess programming", "chess programming is interesting", multipartFile2)
        then:
            Tag.list().size() == 2
    }

    void "can a tag be edited"(){
        when:
            Tag tag1 = service.editTag("on", "cool-music","music", "music education is important", multipartFile1)
            Tag updatedTag = service.editTag("", "free oranges","oranges", "oranges have a great aroma and taste great too", multipartFile2, tag1.id)
        then:
            def result = Tag.findById(tag1.id)
            result.name == "oranges"
            result.shortUrl == "free oranges"
            result.enabled == false
            result.description == "oranges have a great aroma and taste great too"
            result.imageName == multipartFile2.name
            result.imageContentType == multipartFile2.contentType
            result.imageBytes == multipartFile2.bytes


    }

    void "can a tag be deleted"(){
        when:
        Tag tag1 = service.editTag("on", "nice","music", "music education is important", multipartFile1)
        service.deleteTag(tag1.id)

        then:
        Tag.list().size() == 0
    }

    void "when tag is deleted that has an associated post, does it display a nice error"(){
        when:
        Tag.metaClass.static.delete = { ->
            throw new Exception()
        }
        Post post = new Post(title: "Fallout VR 4 On Occulus Rift", content: "Fallout 4 on PC with occulus rift is a fun experience.  Remember how freaked out you were when those radioactive zombies came running right at you?  It's even more fun in VR!",
                summary: "A summary of my experiences with running Fallout 4 VR on occulus rift.  The unofficially unsupported experience!", shortUrl: "occulus-fallout-4-vr", enabled: true,
                imageBytes: image1.bytes, imageContentType: "image/jpg", imageName: "800px_COLOURBOX10725277.jpg")
        Tag tag = new Tag(enabled: true, shortUrl: "music-times", name: "music", description: "music is the best outlit", imageBytes: image1.bytes, imageName: image1.name, imageContentType: "image/jpg").save(failOnError:true, flush:true)
        post.addToTags(tag)
        post.save(failOnError:true, flush:true)
        def result = service.deleteTag(tag.id)

        then:
        result == null
        !(result instanceof Tag)
    }

}
