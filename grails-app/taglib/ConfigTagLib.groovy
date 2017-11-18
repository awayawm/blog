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

    def insertGoogleAnalytics = { attrs, body ->
        out << "<script async src='https://www.googletagmanager.com/gtag/js?id='${Config.findById(1).analyticsKey}'></script>" +
                "<script>" +
                "window.dataLayer = window.dataLayer || [];" +
                "function gtag(){dataLayer.push(arguments)};" +
                "gtag('js', new Date());" +
                "gtag('config', '${Config.findById(1).analyticsKey}');" +
                "</script>"
    }

    def insertGoogleCaptcha = { attrs, body ->
        if(Config.findById(1).enableCaptcha) {
            out << "<input type='submit' class='g-recaptcha btn btn-lg btn-primary btn-block'" +
                    "value='Login' data-sitekey='${Config.findById(1).recaptchaKey}' data-callback='submit'>"
        } else {
            out << "<input type='submit' class='btn btn-lg btn-primary btn-block'" +
                    "value='Login' id='submit'>"
        }
    }

}
