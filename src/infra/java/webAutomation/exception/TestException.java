package webAutomation.exception;

import org.apache.logging.log4j.Logger;
import webAutomation.logger.LoggerManager;

public class TestException extends RuntimeException {
    protected Logger logger = LoggerManager.getLogger(TestException.class);

    public TestException(String msg) {
        this(null, msg);
    }

    public TestException(Throwable cause) {
        this(cause, "");
    }

    public TestException(Throwable cause, String msg) {
        this(cause, msg, true);
    }

    public TestException(Throwable cause, String msg, boolean bPrintException) {
        super(msg, cause);

        if (bPrintException) {
            logger.debug("**Exception caught: " + msg + "\n");
            this.printStackTrace();
        }
    }
}

