package webAutomation.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

//    protected Logger logger = LogManager.getLogger (RetryAnalyzer.class);
//    private static final int    maxTry = 3;
//    private              int    count  = 0;
//
//    @Override
//    public boolean retry (final ITestResult iTestResult) {
//        if (!iTestResult.isSuccess ()) {
//            if (this.count < maxTry) {
//                logger.warn ("Retrying test " + iTestResult.getName () + " with status " + getResultStatusName (
//                        iTestResult.getStatus ()) + " for the " + (this.count + 1) + " time(s).");
//                this.count++;
//                return true;
//            }
//        }
//        return false;
//    }
//    public String getResultStatusName (final int status) {
//        String resultName = null;
//        if (status == 1) {
//            resultName = "SUCCESS";
//        }
//        if (status == 2) {
//            resultName = "FAILURE";
//        }
//        if (status == 3) {
//            resultName = "SKIP";
//        }
//        return resultName;
//    }


    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 3;

    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }

}
