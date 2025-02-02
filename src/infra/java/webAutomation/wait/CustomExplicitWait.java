package webAutomation.wait;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import webAutomation.logger.LoggerManager;
import java.time.Duration;
import java.util.ArrayList;
import static webAutomation.driver.DriverHandler.getDriver;

public class CustomExplicitWait {
    private static Logger logger = LoggerManager.getLogger(CustomExplicitWait.class);

    public CustomExplicitWait() {
    }

    public static ExpectedCondition<Boolean> waitDOMReadyState() {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }

            @Override
            public String toString() {
                return String.format("wait until document ready state to be complete");
            }
        };
    }

    public static Boolean pageFullyLoaded() {
        return pageFullyLoaded(Duration.ofSeconds(30), Duration.ofSeconds(1));
    }

    public static Boolean pageFullyLoaded(Duration timeout, Duration interval) {
        try {
            logger.debug("CustomExplicitWait - wait for page fully load for Duration: "+timeout+" and Interval: "+interval);
            return new FluentWait<WebDriver>(getDriver())
                    .withTimeout(timeout).pollingEvery(interval)
                    .ignoreAll(new ArrayList<Class<? extends Throwable>>(){
                        {
                            add(TimeoutException.class);
                        }
                    })
                    .until(new ExpectedCondition<Boolean>() {

                        int size = 0;
                        int equalsTimes = 0;
                        @Override
                        public Boolean apply(WebDriver driver) {
                            int tempSize = driver.findElements(By.xpath("//*")).size();
                            if (tempSize == size) {
                                equalsTimes++;
                            }
                            else{
                                size = tempSize;
                            }
                            return equalsTimes == 2;
                        }
                        @Override
                        public String toString() {
                            return String.format("wait until page fully loaded");
                        }
                    });
        } catch (Exception e){
            logger.debug("failed on wait for page to fully load");
            return false;
        }
    }






}
