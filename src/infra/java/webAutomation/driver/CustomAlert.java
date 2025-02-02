package webAutomation.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import webAutomation.logger.LoggerManager;

public class CustomAlert implements Alert {
    protected Logger logger = LoggerManager.getLogger(CustomAlert.class);
    WebDriver driver;

    public CustomAlert(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void dismiss() {
        logger.debug("");
        driver.switchTo().alert().dismiss();
    }

    @Override
    public void accept() {
        logger.debug("");
        driver.switchTo().alert().accept();
    }

    @Override
    public String getText() {
        String alertText = driver.switchTo().alert().getText();
        logger.debug(alertText);
        return alertText;
    }

    @Override
    public void sendKeys(String keysToSend) {
        logger.debug(keysToSend);
        driver.switchTo().alert().sendKeys(keysToSend);
    }
}
