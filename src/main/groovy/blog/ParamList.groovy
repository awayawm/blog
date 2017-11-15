package blog;

import grails.web.api.WebAttributes

class ParamList implements WebAttributes {
    String key
    ParamList(String key) { this.key = key }

    List<String> ReturnParamList() {
        List<String> tagsList = new ArrayList<>()
        String tags = getParams().list(this.key + '[]')
        String[] tagsArray = tags.substring(1, tags.length()-1).tokenize(',')
        tagsArray.eachWithIndex { tag, index ->
            if (index > 0) {
                tag = tag.substring(1)
            }
            tagsList.add(tag)
//                    println Tag.findByName(tag)
        }
        return tagsList
    }
}
