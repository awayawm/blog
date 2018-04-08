package blog

class ConfigService {

    def getConfig(){
//        log.info "blog-config system property set to ${System.getProperty("blog-config")}"
        new ConfigSlurper().parse(new File(System.getProperty("blog-config")).text)
    }

}
