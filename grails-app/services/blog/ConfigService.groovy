package blog

class ConfigService {
    static ConfigObject configObject = null
    def getConfig(){
        if (!configObject) {
            log.info "blog-config system property set to ${System.getProperty("blog-config")}"
            if(System.getProperty("BLOG_CONFIG"))
                configObject = new ConfigSlurper().parse(new File(System.getProperty("BLOG_CONFIG")).text)
            else
                configObject = new ConfigSlurper().parse(this.class.classLoader.getResource("blog.config"))
        }
        return configObject
    }
}
