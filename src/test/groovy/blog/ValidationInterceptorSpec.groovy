package blog

import blog.ValidationInterceptor
import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class ValidationInterceptorSpec extends Specification implements InterceptorUnitTest<ValidationInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

//    void "Test validation interceptor matching"() {
//        when:"A request matches the interceptor"
//            withRequest(controller:"validation")
//
//        then:"The interceptor does match"
//        true
//            interceptor.doesMatch()
//    }
}
