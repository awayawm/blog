package blog

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.grails.plugins.testing.GrailsMockMultipartFile
import spock.lang.Specification

class PostServiceSpec extends Specification implements ServiceUnitTest<PostService>, DataTest{
    File image1
    File image2
    File image3
    File image4
    File image5
    File image6
    File image7
    Tag tag1
    Tag tag2
    Tag tag3
    Post post1
    Post post2
    Post post3

    void setupSpec(){
        mockDomain Tag
        mockDomain Post
    }

    void setup(){
        image1 = new File(this.class.classLoader.getResource("../../resources/test/images/Music-Note.jpg").toURI())
        image2 = new File(this.class.classLoader.getResource("../../resources/test/images/staunton-chess-set-1.jpg").toURI())
        image3 = new File(this.class.classLoader.getResource("../../resources/test/images/ThinkstockPhotos-494037394.jpg").toURI())

        image4 = new File(this.class.classLoader.getResource("../../resources/test/images/800px_COLOURBOX10725277.jpg").toURI())
        image5 = new File(this.class.classLoader.getResource("../../resources/test/images/d_SC_DETAIL_MODULE1_720x690_2.2_LowerBackPain.jpg").toURI())
        image6 = new File(this.class.classLoader.getResource("../../resources/test/images/hooked-on-code_icon_logo_RGB.png").toURI())
        image7 = new File(this.class.classLoader.getResource("../../resources/test/images/images.png").toURI())

        tag1 = new Tag(name: "music", description: "music is the best outlit", imageBytes: image1.bytes, imageName: image1.name, imageContentType: "image/jpg")
        tag2 = new Tag(name: "chess programming", description: "chess programming is interesting", imageBytes: image2.bytes, imageName: image2.name, imageContentType: "image/jpg")
        tag3 = new Tag(name: "oranges", description: "oranges have a great aroma and taste great too", imageBytes: image3.bytes, imageName: image3.name, imageContentType: "image/jpg")

        post1 = new Post(title: "Fallout VR 4 On Occulus Rift", content: "Fallout 4 on PC with occulus rift is a fun experience.  Remember how freaked out you were when those radioactive zombies came running right at you?  It's even more fun in VR!",
                summary: "A summary of my experiences with running Fallout 4 VR on occulus rift.  The unofficially unsupported experience!", shortUrl: "occulus-fallout-4-vr", enabled: true,
                imageBytes: image4.bytes, imageContentType: "image/jpg", imageName: "800px_COLOURBOX10725277.jpg")

        post2 = new Post(title: "Chairs.  Which ones are best for your back?", content: "Recently I was shopping at the Lumbar Yard, looking for different furniture that'll be good for my back.  I need a new bed and chair and a couch and need lower back support.  The employees were very helpful, now it's your turn!",
                summary: "Maintaining a healthy lower back is key to a long and fulfilled life.  Most successful people will admit that their lower back was their key to success.  Why can't it be yours too?  Check out this post for more.", shortUrl: "lower-back-and-you", enabled: true,
                imageBytes: image5.bytes, imageContentType: "image/jpg", imageName: "d_SC_DETAIL_MODULE1_720x690_2.2_LowerBackPain.jpg")

        post3 = new Post(title: "Programming Languages and Business Needs", content: "Every organization at some point must decide which languages best suite their business needs.  For some this is javascript, for others this is QBasic, and for others VBA and spreadsheet macros are enough to get through the quarter.",
                summary: "Each programming language has traits and characteristics that may make it particularly suitable for a particular task.  We'll consider some of those in this article", shortUrl: "programming-languages-business-needs", enabled: true,
                imageBytes: image6.bytes, imageContentType: "image/png", imageName: "hooked-on-code_icon_logo_RGB.png")

    }


    void "can post be added"(){
        when:
        GrailsMockMultipartFile mockMultipartFile = new GrailsMockMultipartFile(post1.imageName, post1.imageName, post1.imageContentType, post1.imageBytes)
        def post = service.addPost(post1.title, post1.content, post1.summary, post1.shortUrl, true, mockMultipartFile, [tag1.save(flush:true).id, tag3.save(flush:true).id])
        then:
        Post.list().size() == 1
        Post.findById(post.id).tags.size() == 2
    }

    void "can post be edited, new photo"(){
        when:
            GrailsMockMultipartFile mockMultipartFile = new GrailsMockMultipartFile(post1.imageName, post1.imageName, post1.imageContentType, post1.imageBytes)
            GrailsMockMultipartFile mockMultipartFile2 = new GrailsMockMultipartFile(post2.imageName, post2.imageName, post2.imageContentType, post2.imageBytes)
            def post = service.addPost(post1.title, post1.content, post1.summary, post1.shortUrl, true, mockMultipartFile, [tag1.save(flush:true).id, tag3.save(flush:true).id])
            post = service.editPost(post.id, post2.title, post2.content, post2.summary, post2.shortUrl, true, mockMultipartFile2, [tag2.save(flush:true).id])

        then:
            Post.findById(post.id).imageName == "d_SC_DETAIL_MODULE1_720x690_2.2_LowerBackPain.jpg"
            Post.findById(post.id).title == "Chairs.  Which ones are best for your back?"
            Post.findById(post.id).tags.size() == 1
    }

    void "can post be deleted"(){
        when:
            GrailsMockMultipartFile mockMultipartFile = new GrailsMockMultipartFile(post1.imageName, post1.imageName, post1.imageContentType, post1.imageBytes)
            def post = service.addPost(post1.title, post1.content, post1.summary, post1.shortUrl, true, mockMultipartFile, [tag1.save(flush:true).id, tag3.save(flush:true).id])
            def postResult = service.deletePost(post.id)
        then:
            Post.list().size() == 0
            postResult == null
    }

}
