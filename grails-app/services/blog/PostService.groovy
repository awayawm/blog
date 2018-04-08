package blog

import org.springframework.web.multipart.MultipartFile

class PostService {

    def addPost(String title, String content, String summary, String shortUrl, Boolean enabled, MultipartFile multipartFile, List tags) {
        Post post = new Post(title: title, content: content, summary: summary, shortUrl: shortUrl, enabled: enabled,
                imageBytes: multipartFile.getBytes(), imageContentType: multipartFile.getContentType(),
                imageName: multipartFile.getName())
        tags.each {
            post.addToTags(Tag.findById(it))
        }
        post.save(flush: true, failOnError: true)
    }

    def editPost(Long id, String title, String content, String summary, String shortUrl,
                 Boolean enabled, MultipartFile multipartFile, List tags) {
        Post post = Post.findById(id)
        if(post){
            post.title = title
            post.content = content
            post.summary = summary
            post.shortUrl = shortUrl
            post.enabled = enabled

            if(multipartFile){
                post.imageName = multipartFile.getName()
                post.imageContentType = multipartFile.getContentType()
                post.imageBytes = multipartFile.getBytes()
            }

            post.tags.clear()

            tags.each {
                post.addToTags(Tag.findById(it))
            }
            post.save(flush: true, failOnError: true)
        }
        post
    }

    def deletePost(Long id){
        Post post = Post.findById(id)
        if(post){
            post = post.delete(flush:true)
        }
        post
    }
}