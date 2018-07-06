package blog

import javax.servlet.ServletContext

class BootStrap {

    def init = { ServletContext servletContext ->

        log.info "servletContent resource folders - ${servletContext.getRealPath("/").concat("blog.config")}"

//            getResource("/blog.config").path}"
        ConfigService configService = new ConfigService()

        if(System.getProperty("BLOG_CONFIG") == null){
            log.info "Loading config from blog-config system property from ${servletContext.getRealPath("/").concat("blog.config")}"
            System.setProperty("BLOG_CONFIG", servletContext.getRealPath("/").concat("blog.config"))
        }

        if(User.list().size() == 0 && Role.list().size() == 0){
            log.info "Creating admin user with credentials from config file.  Username: ${configService.getConfig().username}, Password: ${configService.getConfig().password}"
            User user = new User(username:configService.getConfig().username, password:configService.getConfig().password).save(flush:true)
            Role role = new Role(authority: "ROLE_ADMIN").save(flush:true)
            UserRole.create user, role
        }

        if(Post.list().size() == 0) {

            File image1 = new File(servletContext.getRealPath("images/Music-Note.jpg"))
            File image2 = new File(servletContext.getRealPath("images/staunton-chess-set-1.jpg"))
            File image3 = new File(servletContext.getRealPath("images/ThinkstockPhotos-494037394.jpg"))

            File image4 = new File(servletContext.getRealPath("images/800px_COLOURBOX10725277.jpg"))
            File image5 = new File(servletContext.getRealPath("images/d_SC_DETAIL_MODULE1_720x690_2.2_LowerBackPain.jpg"))
            File image6 = new File(servletContext.getRealPath("images/hooked-on-code_icon_logo_RGB.png"))
            File image7 = new File(servletContext.getRealPath("images/images.png"))

            Tag tag1 = new Tag(enabled: true, shortUrl: "learn-music", name: "music", description: "music is the best outlit", imageBytes: image1.bytes, imageName: image1.name, imageContentType: "image/jpg").save(failOnError: true, flush: true)
            Tag tag2 = new Tag(enabled: false, shortUrl: "chess-programming", name: "chess programming", description: "chess programming is interesting", imageBytes: image2.bytes, imageName: image2.name, imageContentType: "image/jpg").save(failOnError: true, flush: true)
            Tag tag3 = new Tag(enabled: true, shortUrl: "oranges", name: "oranges", description: "oranges have a great aroma and taste great too", imageBytes: image3.bytes, imageName: image3.name, imageContentType: "image/jpg").save(failOnError: true, flush: true)


            Post post1 = new Post(title: "Fallout VR 4 On Occulus Rift", content: "Fallout 4 on PC with occulus rift is a fun experience.  Remember how freaked out you were when those radioactive zombies came running right at you?  It's even more fun in VR!",
                    summary: "A summary of my experiences with running Fallout 4 VR on occulus rift.  The unofficially unsupported experience!", shortUrl: "occulus-fallout-4-vr", enabled: true,
                    imageBytes: image4.bytes, imageContentType: "image/jpg", imageName: "800px_COLOURBOX10725277.jpg").save(failOnError: true, flush: true)

            post1.addToTags(tag1)
            post1.addToTags(tag3)

            Post post2 = new Post(title: "Chairs.  Which ones are best for your back?", content: "<i>Recently I was shopping at the Lumbar Yard</i>, looking for different furniture that'll be good for my back.  I need a new bed and chair and a couch and need lower back support.  The employees were very helpful, now it's your turn!",
                    summary: "Maintaining a healthy lower back is key to a long and fulfilled life.  Most successful people will admit that their lower back was their key to success.  Why can't it be yours too?  Check out this post for more.", shortUrl: "lower-back-and-you", enabled: true,
                    imageBytes: image5.bytes, imageContentType: "image/jpg", imageName: "d_SC_DETAIL_MODULE1_720x690_2.2_LowerBackPain.jpg").save(failOnError: true, flush: true)

            post2.addToTags(tag2)

            Post post3 = new Post(title: "Programming Languages and Business Needs", content: "Every organization at some point must decide which languages best suite their business needs.  For some this is javascript, for others this is QBasic, and for others VBA and spreadsheet macros are enough to get through the quarter.",
                    summary: "Each programming language has traits and characteristics that may make it particularly suitable for a particular task.  We'll consider some of those in this article", shortUrl: "programming-languages-business-needs", enabled: true,
                    imageBytes: image6.bytes, imageContentType: "image/png", imageName: "hooked-on-code_icon_logo_RGB.png").save(failOnError: true, flush: true)

            post3.addToTags(tag3)
            post3.addToTags(tag2)
            post3.addToTags(tag1)

        }
    }

    def destroy = {
    }
}
