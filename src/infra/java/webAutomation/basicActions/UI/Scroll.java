package webAutomation.basicActions.UI;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import webAutomation.elements.base.CustomWebElement;
import webAutomation.logger.LoggerManager;
import webAutomation.driver.CustomWebDriver;


public class Scroll {
    protected Logger logger = LoggerManager.getLogger(Scroll.class);
    protected CustomWebDriver driver;

    public Scroll(CustomWebDriver driver){
        this.driver = driver;
    }
    public void scrollUntil(int x, int y){
        ((JavascriptExecutor)driver).executeScript("window.scrollBy("+String.valueOf(x)+","+String.valueOf(y)+")","");
    }

    public void scrollUntil(CustomWebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
    }

}
