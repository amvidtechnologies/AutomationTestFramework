package webAutomation.retry;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.Logger;
import webAutomation.exception.TestException;
import webAutomation.logger.LoggerManager;

public class Retry {

    protected Logger logger = LoggerManager.getLogger(Retry.class);
    private int retryCount;
    private int waitTimeBetweenAttempts;

    public Retry(int retryCount, int waitTimeBetweenAttempts) {
        this.retryCount = retryCount;
        this.waitTimeBetweenAttempts = waitTimeBetweenAttempts;
    }

    public void actionToTry(IRetry iRetry) throws Throwable {
        innerActionToTry(iRetry);
    }

    public void innerActionToTry(IRetry iRetry) throws Throwable {
        Validate.notNull(retryCount, "must set retryCount");
        String callerMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        int count = 1;
        while (count <= retryCount) {
            try {
                logger.debug("{} : actionToTry -> iRetry.onAttempt(), count = " + count + " -> " + "attempt to action : " + callerMethodName);
            } catch (Exception e) {
                logger.debug("{} : actionToTry -> iRetry.onAttempt(), count = " + count + " -> " + "attempt to Fail action : " + callerMethodName);
            }
            Thread.sleep(waitTimeBetweenAttempts);
            try {
                logger.debug("{} : actionToTry -> iRetry.onAttemptFail(), count = " + count + " -> " + "attempted Fail to perform action : " + callerMethodName);
            } catch (Exception e) {
                logger.debug("{} : actionToTry -> iRetry.onAttemptFail() threw Exception " + callerMethodName);
            }
        }
        throw new TestException(callerMethodName + " : Retry -> actionToTry -> failed to retry action.");
    }
}