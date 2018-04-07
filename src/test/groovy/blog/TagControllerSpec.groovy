package blog

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import org.grails.plugins.testing.GrailsMockMultipartFile
import spock.lang.Specification

class TagControllerSpec extends Specification implements ControllerUnitTest<TagController>, DataTest{

    void setupSpec(){
        mockDomain Tag
    }

    TagService tagService = new TagService()

    File image1 = new File(this.class.classLoader.getResource("../../resources/test/images/Music-Note.jpg").toURI())
    File image2 = new File(this.class.classLoader.getResource("../../resources/test/images/staunton-chess-set-1.jpg").toURI())
    File image3 = new File(this.class.classLoader.getResource("../../resources/test/images/ThinkstockPhotos-494037394.jpg").toURI())

    GrailsMockMultipartFile multipartFile1 = new GrailsMockMultipartFile("multipartFile1", "multipartFile1", "image/jpeg", image1.bytes)
    GrailsMockMultipartFile multipartFile2 = new GrailsMockMultipartFile("multipartFile2", "multipartFile2", "image/jpeg", image2.bytes)
    GrailsMockMultipartFile multipartFile3 = new GrailsMockMultipartFile("multipartFile3", "multipartFile3", "image/jpeg", image3.bytes)

    Tag tag1 = tagService.editTag("music", "music education is important", multipartFile1)
    Tag tag3 = tagService.editTag("oranges", "oranges have a great aroma and taste great too", multipartFile3)
    Tag tag2 = tagService.editTag("chess programming", "chess programming is interesting", multipartFile2)

    void "can tag be added submission"(){
        when:
            params.name = "music"
            params.description = "music education is important"
            params.image = multipartFile1
            controller.addEdit()
        then:
            Tag.list().size() == 1
            controller.flash.message == "Added new tag successfully."
            controller.flash.class == "alert alert-success"
            response.redirectUrl == "/admin/tags"
    }


    void "if image not include with update, dont overwrite image"(){
        // if not resubmitting image, don't update
        when:
            tag1.save()
            params.name = "fuku fuku"
            params.description = "kitten tales 2"
            params.id = tag1.id
            controller.addEdit()
        then:
            Tag.findById(tag1.id).imageBytes == image1.bytes

    }

    void "can tag be edited?"(){
        when:
            tag1.save()
            params.name = "anime"
            params.description = "they're all good"
            params.image = multipartFile3
            params.id = tag1.id
            controller.addEdit()
        then:
            Tag.findById(tag1.id).name == "anime"
            Tag.findById(tag1.id).description == "they're all good"
            Tag.findById(tag1.id).imageBytes == multipartFile3.bytes
    }

    void "can tag be deleted"(){
        when:
            tag1.id = 1
            tag1.save()
            params.id = tag1.id
            controller.deleteTag()
        then:
            !Tag.list().size()
    }

    void "can all tags be retrieved"(){
        when:
        tag1.id = 1
        tag2.id = 2
        tag3.id = 3
        tag1.save()
        tag2.save()
        tag3.save()
        def result = controller.index()

        then:
        model.tags.size() == 3
    }

    void "does controller return single tag when passed id"(){
        when:
            tag1.id = 1
            tag2.id = 2
            tag1.save()
            tag2.save()
            params.id = 2
            controller.index()
        then:
            model.name == "chess programming"
            model.description == "chess programming is interesting"
    }
}
