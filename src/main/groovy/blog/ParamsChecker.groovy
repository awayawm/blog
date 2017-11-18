package blog

import grails.web.api.WebAttributes

class ParamsChecker implements WebAttributes {
    List<String> requiredParams
    List<String> paramsPresent = []

    ParamsChecker(List<String> requiredParams) {
//        requiredParams.each{ k -> println "${k}" }
        this.requiredParams = requiredParams
    }

    def areRequirementsPresent() {
        requiredParams.each { requirement ->
            if(getParams()[requirement] != "" && getParams()[requirement] != null)
                paramsPresent.add(requirement)
        }

        if(paramsPresent != requiredParams)
            println 'required paramenters are not present.'

        return paramsPresent == requiredParams
    }
}
