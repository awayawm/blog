package blog

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class PostControllerSpec extends Specification implements DataTest, ControllerUnitTest<PostController>{
    def setupSpec(){
        mockDomain Post
    }

    def setup(){
        File image1 = new File(servletContext.getResource("/images/Music-Note.jpg").toURI())
        File image2 = new File(servletContext.getResource("/images/staunton-chess-set-1.jpg").toURI())
        File image3 = new File(servletContext.getResource("/images/ThinkstockPhotos-494037394.jpg").toURI())

        new Tag(name: "music", description: "music is the best outlit", imageBytes: image1.bytes, imageName: image1.name, imageContentType: "image/jpg").save(failOnError:true)
        new Tag(name: "chess programming", description: "chess programming is interesting", imageBytes: image2.bytes, imageName: image2.name, imageContentType: "image/jpg").save(failOnError:true)
        new Tag(name: "oranges", description: "oranges have a great aroma and taste great too", imageBytes: image3.bytes, imageName: image3.name, imageContentType: "image/jpg").save(failOnError:true)
    }

    void "post index returns all tags"(){
        when:
        controller.index()
        then:
        model.tags.size() == 3
    }
}
