package blog

import grails.web.api.WebAttributes

class ParamsChecker implements WebAttributes {
    def requiredParams

    ParamsChecker(List<String> requiredParams) {
        map.each{ k, v -> println "${k}:${v}" }
        this.requiredParams = requiredParams
    }
}
