package blog

import grails.testing.gorm.DataTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class RssControllerSpec extends Specification implements ControllerUnitTest<RssController>, DataTest{
    void setupSpec(){
        mockDomain Post
    }
    void setup(){
        def image1 = new File(this.class.classLoader.getResource("images/Music-Note.jpg").toURI())
        def image2 = new File(this.class.classLoader.getResource("images/staunton-chess-set-1.jpg").toURI())
        def image3 = new File(this.class.classLoader.getResource("images/ThinkstockPhotos-494037394.jpg").toURI())

        def image4 = new File(this.class.classLoader.getResource("images/800px_COLOURBOX10725277.jpg").toURI())
        def image5 = new File(this.class.classLoader.getResource("images/d_SC_DETAIL_MODULE1_720x690_2.2_LowerBackPain.jpg").toURI())
        def image6 = new File(this.class.classLoader.getResource("images/hooked-on-code_icon_logo_RGB.png").toURI())
        def image7 = new File(this.class.classLoader.getResource("images/images.png").toURI())

        def tag1 = new Tag(enabled: true, shortUrl: "music", name: "music", description: "music is the best outlit", imageBytes: image1.bytes, imageName: image1.name, imageContentType: "image/jpg")
        def tag2 = new Tag(enabled: false, shortUrl: "chess-programming", name: "chess programming", description: "chess programming is interesting", imageBytes: image2.bytes, imageName: image2.name, imageContentType: "image/jpg")
        def tag3 = new Tag(enabled: false, shortUrl: "georgia-oranges", name: "oranges", description: "oranges have a great aroma and taste great too", imageBytes: image3.bytes, imageName: image3.name, imageContentType: "image/jpg")

        def post1 = new Post(title: "Fallout VR 4 On Occulus Rift", content: "Fallout 4 on PC with occulus rift is a fun experience.  Remember how freaked out you were when those radioactive zombies came running right at you?  It's even more fun in VR!",
                summary: "A summary of my experiences with running Fallout 4 VR on occulus rift.  The unofficially unsupported experience!", shortUrl: "occulus-fallout-4-vr", enabled: true,
                imageBytes: image4.bytes, imageContentType: "image/jpg", imageName: "800px_COLOURBOX10725277.jpg")

        def post2 = new Post(title: "Chairs.  Which ones are best for your back?", content: "Recently I was shopping at the Lumbar Yard, looking for different furniture that'll be good for my back.  I need a new bed and chair and a couch and need lower back support.  The employees were very helpful, now it's your turn!",
                summary: "Maintaining a healthy lower back is key to a long and fulfilled life.  Most successful people will admit that their lower back was their key to success.  Why can't it be yours too?  Check out this post for more.", shortUrl: "lower-back-and-you", enabled: true,
                imageBytes: image5.bytes, imageContentType: "image/jpg", imageName: "d_SC_DETAIL_MODULE1_720x690_2.2_LowerBackPain.jpg")

        def post3 = new Post(title: "Programming Languages and Business Needs", content: "Every organization at some point must decide which languages best suite their business needs.  For some this is javascript, for others this is QBasic, and for others VBA and spreadsheet macros are enough to get through the quarter.",
                summary: "Each programming language has traits and characteristics that may make it particularly suitable for a particular task.  We'll consider some of those in this article", shortUrl: "programming-languages-business-needs", enabled: true,
                imageBytes: image6.bytes, imageContentType: "image/png", imageName: "hooked-on-code_icon_logo_RGB.png")

        tag1.save(flush: true)
        tag2.save(flush: true)
        tag3.save(flush: true)
        post1.addToTags(tag1)
        post1.addToTags(tag3)
        post2.addToTags(tag2)
        post1.save(flush: true)
        post2.save(flush: true)
    }
    void cleanup(){}
    void getXmlFromEndpoint(){
        when:
        controller.index()
        then:
        def xml = new XmlSlurper().parseText(response.getText())
        xml."**".findAll { it.name() == "item" }.size() == 1
    }
}
