package webAutomation.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import webAutomation.logger.LoggerManager;

import java.net.URL;

public class CustomNavigation implements WebDriver.Navigation {
    protected Logger logger = LoggerManager.getLogger(CustomNavigation.class);
    private WebDriver driver;

    public CustomNavigation(WebDriver driver){
        this.driver = driver;
    }
    @Override
    public void back() {
        logger.debug("");
        driver.navigate().back();
    }

    @Override
    public void forward() {
        logger.debug("");
        driver.navigate().forward();
    }

    @Override
    public void to(String url) {
        logger.debug(url);
        driver.navigate().to(url);
    }

    @Override
    public void to(URL url) {
        logger.debug(url.toString());
        driver.navigate().to(url);
    }

    @Override
    public void refresh() {
        logger.debug("");
        driver.navigate().refresh();
    }
}
