package webAutomation.logger;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LoggerManager {

    public static Logger getLogger(final Class<?> clazz){
        return LogManager.getLogger(clazz);
    }
}
