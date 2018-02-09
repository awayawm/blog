package blog

class BootStrap {

    def init = { servletContext ->
        def config = Config.findById(1)
        if(config == null) {
            config = new Config(
                    id: 1,
                    shortTokenTimer: 20 * 60 * 1000,
                    longTokenTimer: 60 * 60 * 1000,
                    siteTitle: "My blog",
                    title: "Welcome to My blog",
                    tagline: "Life's a journey!",
                    favicon: "none.ico",
                    enableCaptcha: false,
                    recaptchaKey: 'none',
                    analyticsKey: 'none'
            )
            config.save()
        }
    }
    def destroy = {
    }
}
