package webAutomation.basePages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webAutomation.basicActions.UI.Scroll;
import webAutomation.driver.CustomWebDriver;
import webAutomation.elements.base.CustomWebElement;
import webAutomation.logger.LoggerManager;
import webAutomation.wait.CustomExplicitWait;
import webAutomation.wait.CustomWebDriverWait;

import java.time.Duration;

public class BasePage implements BasePageInterface{
    protected Logger logger = LoggerManager.getLogger(BasePage.class);
    protected CustomWebDriver driver;
    protected CustomWebDriverWait wait;
    private Scroll scroll;
    public BasePage(CustomWebDriver driver){
        this.driver = driver;
        scroll = new Scroll (driver);
        wait = new CustomWebDriverWait(driver,15,1000);
    }

    public void openBrowserWithURL(String URL){
        logger.debug("Open browser with URL, delete all cookies and maximize window.");
        driver.manage().deleteAllCookies();
        driver.get(URL);
        driver.manage().window().maximize();
        CustomExplicitWait.pageFullyLoaded(Duration.ofSeconds(pageFullyLoadTimeOutInSeconds),Duration.ofMillis(elementSleepInMillis));
    }

   public CustomWebElement element(WebElement element){
       CustomWebElement customWebElement = new CustomWebElement(element,elementTimeOutInSeconds,elementSleepInMillis);
       wait.until(ExpectedConditions.visibilityOf(customWebElement)).isDisplayed();
       try {
           scroll.scrollUntil(customWebElement);
           scroll.scrollUntil(0,-50);
       }
       catch (Exception e){
           logger.debug("unable to scroll to element: element :{}");
       }
       return customWebElement;
    }
}
