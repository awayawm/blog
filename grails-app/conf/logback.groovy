import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
}

appender("WEBAPP", RollingFileAppender) {
    file = "/var/log/tomcat/blog.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%-50(%date{ISO8601} [%thread]) %-5level %logger{50} - %msg%n%rEx"
    }
    triggeringPolicy(SizeBasedTriggeringPolicy) {
        maxFileSize = '10MB'
    }
    rollingPolicy(FixedWindowRollingPolicy) {
        fileNamePattern = "/var/log/tomcat/blog-%d{yyyyMMdd_hhmmss}.%i.gz"
        maxIndex = 10
    }
}

root(ERROR, ['STDOUT'])
root(INFO, ['STDOUT'])
root(ERROR, ['WEBAPP'])
root(INFO, ['WEBAPP'])