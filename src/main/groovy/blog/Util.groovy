package blog

import groovy.xml.MarkupBuilder

import java.text.DateFormat
import java.text.SimpleDateFormat

class Util {
    ConfigService configService = new ConfigService()
    def processTags(def posts){
        def postsWithEnabledTags = []
        posts.each { post ->
            def tags = []
            post.tags.each { tag ->
                if(tag.enabled) tags << tag
            }
            post.tags = []
            post.tags = tags
            if (post.tags.size() != 0) postsWithEnabledTags << post
        }
        postsWithEnabledTags
    }

    String getRssFeed(def posts) {
        def xmlWriter = new StringWriter()
        def xmlMarkup = new MarkupBuilder(xmlWriter)
        DateFormat pubDateFormatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)

        xmlMarkup.rss(version: "2.0") {
            channel() {
                title(configService.getConfig().title)
                link(configService.getConfig().url)
                description(configService.getConfig().tagline)
                image() {
                    url(configService.getConfig().favicon)
                    title(configService.getConfig().title)
                    link(configService.getConfig().url)
                }
                posts.each { Post post ->
                    item() {
                        title(post.title)
                        link(configService.getConfig().url.toString() + "/posts/" + post.shortUrl)
                        pubDate(pubDateFormatter.format(post.lastUpdated))
                        description("<![CDATA[ ${post.content} ]]>")
                    }
                }
            }
        }
        return xmlWriter.toString()
    }

}
