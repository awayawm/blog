package blog

import org.springframework.web.multipart.MultipartFile

class TagService {
    Tag tag = null

    Tag editTag(String enabled, String shortUrl, String name, String description, MultipartFile multipartFile, Long id = null){
        if(id == null) {
            // add tag
            tag = new Tag(name: name,
                    shortUrl: shortUrl,
                    description: description,
                    imageBytes: multipartFile.bytes,
                    imageName: multipartFile.name,
                    enabled: enabled == "on" ? true : false,
                    imageContentType: multipartFile.contentType).save(failOnError: true)
        } else {
            // edit tag
            tag = Tag.findById(id)
            if(tag != null){
                log.info("save edit to tag ${tag.id}")
                tag.name = name
                tag.description = description
                tag.shortUrl = shortUrl
                tag.enabled = enabled == "on" ? true : false
                if (multipartFile != null && !multipartFile.isEmpty()){
                    log.info("new image included, overwriting old image")
                    tag.imageName = multipartFile.name
                    tag.imageBytes = multipartFile.bytes
                    tag.imageContentType = multipartFile.contentType
                }
                tag.save(failOnError: true, flush:true)
            }
        }
        tag
    }

    Tag deleteTag(Long id){
        Tag tag = Tag.findById(id)
        if(tag) {
            try {
                tag = tag.delete()
            } catch(Exception ex){
                return new Tag()
            }
        }
        return tag
    }

}
