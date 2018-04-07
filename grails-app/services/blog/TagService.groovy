package blog

import org.springframework.web.multipart.MultipartFile

class TagService {
    Tag tag = null

    Tag editTag(String name, String description, MultipartFile multipartFile, Long id = null){
        if(id == null) {
            // add tag
            tag = new Tag(name: name,
                    description: description,
                    imageBytes: multipartFile.bytes,
                    imageName: multipartFile.name,
                    imageContentType: multipartFile.contentType).save(failOnError: true)
        } else {
            // edit tag
            tag = Tag.findById(id)
            if(tag != null){
                log.info("save edit to tag ${tag.id}")
                tag.name = name
                tag.description = description
                tag.imageName = multipartFile.name
                tag.imageBytes = multipartFile.bytes
                tag.imageContentType = multipartFile.contentType
                tag.save(failOnError: true)
            }
        }
        tag
    }

    def deleteTag(Long id){
        Tag tag = Tag.findById(id)
        if(tag) {
            tag.delete()
        }
    }

}
