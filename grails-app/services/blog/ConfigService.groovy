package blog

class ConfigService {

    public def config

    Config getConfig(){
        config != null ?: Config.findById(1)
    }

    String getShortTokenTimer(){
        getConfig().shortTokenTimer
    }

    String getLongTokenTimer(){
        getConfig().longTokenTimer
    }

}
