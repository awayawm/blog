package blog

class ConfigTagLib {

    def getConfigSitetitle = { attrs, body ->
         out << Config.findById(1).siteTitle
    }

    def getConfigTitle = { attrs, body ->
        out << Config.findById(1).title
    }

    def getConfigTagline = { attrs, body ->
        out << Config.findById(1).tagline
    }

    def getConfigFavicon = { attrs, body ->
        out << "<link rel='icon' href='${Config.findById(1).favicon}'>"
    }

}
