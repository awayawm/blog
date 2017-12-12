package blog

import java.util.logging.Level
import java.util.logging.Logger

class LogUtil {
    private final static Logger logger = Logger.getLogger("blog")

    static void printLogMessage(String message) {
        logger.setLevel(Level.INFO);
        logger.severe(message)
    }
}