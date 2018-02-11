package blog

import org.apache.log4j.Logger


class ValidationInterceptor {

    public ValidationInterceptor() {
        match controller: 'index'
    }

    boolean before() {
        Logger.getLogger(this.class.name).error("i'm about this schmoney")
        true
    }

    boolean after() { true }

    void afterView() {}
}
