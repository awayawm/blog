package blog

import org.springframework.web.multipart.MultipartFile

class TagService {

    Tag editTag(String name, String description, MultipartFile multipartFile){
        Tag tag = new Tag(name: name,
                description: description,
                imageBytes: multipartFile.bytes,
                imageName: multipartFile.name,
                imageContentType: multipartFile.contentType).save(failOnError:true)

        if(tag != null) {
            return tag
        } else {
            return null
        }
    }

}
