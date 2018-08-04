package blog

import org.springframework.security.access.annotation.Secured

class RssController {
    Util util = new Util()
    @Secured('permitAll')
    def index() {
        def enablePosts = Post.findAll { enabled == true }
        def posts = util.processTags(enablePosts).sort{a,b-> b.lastUpdated<=>a.lastUpdated}
        def rssXml = util.getRssFeed(posts)
        render(text: rssXml, contentType: "text/xml", encoding: "UTF-8")
    }
}
