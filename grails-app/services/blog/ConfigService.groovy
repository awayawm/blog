package blog

class ConfigService {

    public def config

    Config getConfig(){
        config == null ? Config.findById(1) : config
    }

    Integer getShortTokenTimer(){
        getConfig().shortTokenTimer
    }

    Integer getLongTokenTimer(){
        getConfig().longTokenTimer
    }

    String getRecaptchaKey(){
        getConfig().recaptchaKey
    }

    String getSecretKey(){
        System.getenv("SECRET_KEY") ?: "blog"
    }

}
