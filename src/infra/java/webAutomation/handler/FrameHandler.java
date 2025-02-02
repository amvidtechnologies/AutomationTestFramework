package webAutomation.handler;

import webAutomation.driver.CustomWebDriver;
import webAutomation.elements.base.CustomWebElement;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webAutomation.logger.LoggerManager;
import webAutomation.wait.CustomWebDriverWait;

public class FrameHandler {
    protected Logger logger = LoggerManager.getLogger(FrameHandler.class);
    private CustomWebDriver driver;
    private CustomWebDriverWait webDriverWait;

    public FrameHandler(CustomWebDriver driver) {
        this.driver = driver;
        webDriverWait = new CustomWebDriverWait(driver, 15,1000);
    }

    public void iframeHandler(int iframeIndex){
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeIndex));
        driver.switchTo().frame(iframeIndex);
    }
    public void iframeHandler(String iframeID){
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeID));
        driver.switchTo().frame(iframeID);
    }
    public void iframeHandler(CustomWebElement element){
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        driver.switchTo().frame(element);
    }


}