package blog

class ConfigService {

    ConfigObject getConfig(){
        return Config.findById(1)
    }
}
