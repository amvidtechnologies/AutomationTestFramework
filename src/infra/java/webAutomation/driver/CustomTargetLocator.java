package webAutomation.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import webAutomation.logger.LoggerManager;

public class CustomTargetLocator implements WebDriver.TargetLocator {

    protected Logger logger = LoggerManager.getLogger(CustomTargetLocator.class);
    private WebDriver driver;
    private CustomAlert customAlert;

    public CustomTargetLocator (WebDriver driver){
        this.driver = driver;
    }

    @Override
    public WebDriver frame(int index) {
        logger.debug(index);
        return driver.switchTo().frame(index);
    }

    @Override
    public WebDriver frame(String nameOrId) {
        logger.debug(nameOrId);
        return driver.switchTo().frame(nameOrId);
    }

    @Override
    public WebDriver frame(WebElement frameElement) {
        logger.debug(frameElement);
        return driver.switchTo().frame(frameElement);
    }

    @Override
    public WebDriver parentFrame() {
        logger.debug("");
        return driver.switchTo().parentFrame();
    }

    @Override
    public WebDriver window(String nameOrHandle) {
        logger.debug(nameOrHandle);
        return driver.switchTo().window(nameOrHandle);
    }

    @Override
    public WebDriver newWindow(WindowType windowType) {
        logger.debug("");
        return driver.switchTo().newWindow(windowType);
    }

    @Override
    public WebDriver defaultContent() {
            logger.debug("");
            return driver.switchTo().defaultContent();
    }

    @Override
    public WebElement activeElement() {
        logger.debug("");
        return driver.switchTo().activeElement();
    }

    @Override
    public Alert alert() {
        if(customAlert == null){
            customAlert = new CustomAlert(driver);}
        return customAlert;
    }
}
