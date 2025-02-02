package webAutomation.driver;


import webAutomation.elements.base.CustomWebElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import webAutomation.exception.TestException;
import webAutomation.logger.LoggerManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomWebDriver implements WebDriver, HasCapabilities, Interactive, TakesScreenshot, JavascriptExecutor {
    protected Logger logger = LoggerManager.getLogger(CustomWebDriver.class);
    private WebDriver driver;
    private CustomTargetLocator customTargetLocator;
    private CustomNavigation customNavigation;
    private CustomOptions customOptions;
    private DevTools devTools;
    private boolean isLoggerOn = true;


    public CustomWebDriver(BrowserTypes BrowserTypes) {
        this(BrowserTypes, null);
    }
    public CustomWebDriver(BrowserTypes BrowserTypes, MutableCapabilities driverOptions) {
        driver = localDriverSelect(BrowserTypes, driverOptions);
        DriverHandler.setDriver(this);
    }

    @Override
    public Capabilities getCapabilities() {
        logger.debug("driver get capabilities");
        return ((HasCapabilities) driver).getCapabilities();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        if (isLoggerOn()){
            logger.debug("execute java script : "+script);
        }
        setLoggerOn(true);
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        logger.debug("execute java Async script : "+script);
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        logger.debug("driver Take screenshot at target: "+ target.toString());
        return ((TakesScreenshot) driver).getScreenshotAs(target);
    }

    @Override
    public void get(String url) {
        logger.debug("driver get URL: "+url);
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        logger.debug("driver get current URL: "+currentUrl);
        return currentUrl;
    }

    @Override
    public String getTitle() {
        String title = driver.getTitle();
        logger.debug("current title: "+title);
        return title;
    }

    @Override
    public List<WebElement> findElements(By by) {
        logger.debug("driver find elements By: "+by.toString());
        List<WebElement> elementsImpl = new ArrayList<>();
        driver.findElements(by).forEach(element -> elementsImpl.add(element));
        return elementsImpl;
    }

    @Override
    public CustomWebElement findElement(By by) {
        logger.debug("driver find element By: "+by.toString());
        return new CustomWebElement(driver.findElement(by));
    }

    @Override
    public String getPageSource() {
        String pageSource = driver.getPageSource();
        logger.debug("driver get page source: "+pageSource);
        return pageSource;
    }

    @Override
    public void close() {
        logger.debug("driver close the current window");
        driver.close();
    }

    @Override
    public void quit() {
        logger.debug("driver quit a browser session");
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        Set<String> windowHandles = driver.getWindowHandles();
        logger.debug("driver get window handles: "+windowHandles.toString());
        return windowHandles;
    }

    @Override
    public String getWindowHandle() {
        String windowHandle = driver.getWindowHandle();
        logger.debug("driver get windows handle: "+windowHandle);
        return windowHandle;
    }

    @Override
    public TargetLocator switchTo() {
        if (customTargetLocator == null) customTargetLocator = new CustomTargetLocator(driver);
        logger.debug("driver switch to: "+customTargetLocator.toString());
        return customTargetLocator;
    }

    @Override
    public Navigation navigate() {
        if (customNavigation == null) customNavigation = new CustomNavigation(driver);
        logger.debug("driver navigate: "+customNavigation.toString());
        return customNavigation;
    }

    @Override
    public Options manage() {
        if (customOptions == null) customOptions = new CustomOptions(driver);
        logger.debug("driver manage: "+customOptions.toString());
        return customOptions;
    }

    @Override
    public void perform(Collection<Sequence> actions) {
        logger.debug("driver perform action: "+actions.toString());
        ((Interactive) driver).perform(actions);
    }

    @Override
    public void resetInputState() {
        logger.debug("driver reset input state");
        ((Interactive) driver).resetInputState();
    }

    public enum BrowserTypes {
        CHROME, FIREFOX, EDGE, ENV
    }
    public interface BrowserName {
        String CHROME = "chrome";
        String FIREFOX = "firefox";
        String EDGE = "edge";

    }

    private WebDriver localDriverSelect (BrowserTypes BrowserTypes, MutableCapabilities driverOptions){
        logger.debug("web driver select: "+BrowserTypes.toString());
        switch (BrowserTypes) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                if (driverOptions == null) {
                    return new ChromeDriver();
                }
                return new ChromeDriver((ChromeOptions) driverOptions);
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                if (driverOptions == null) {
                    return new FirefoxDriver();
                }
                return new FirefoxDriver((FirefoxOptions) driverOptions);
            case EDGE:
                WebDriverManager.edgedriver().setup();
                if (driverOptions == null) {
                    return new EdgeDriver();
                }
                return new EdgeDriver((EdgeOptions) driverOptions);
            case ENV:
                switch (System.getProperty("browser.type", "chrome")){
                    case BrowserName.CHROME:
                        return localDriverSelect(CustomWebDriver.BrowserTypes.CHROME, driverOptions);
                    case BrowserName.FIREFOX:
                        return localDriverSelect(CustomWebDriver.BrowserTypes.FIREFOX, driverOptions);
                    case BrowserName.EDGE:
                        return localDriverSelect(CustomWebDriver.BrowserTypes.EDGE, driverOptions);
                    default:
                        throw  new IllegalArgumentException("browser type support : chrome, firefox, microsoft edge");
                }
            default:
                throw  new IllegalArgumentException("can use only browser : chrome, firefox, microsoft edge");
        }
    }

    public CustomWebDriver setLoggerOn (boolean isLoggerOn) {
        this.isLoggerOn = isLoggerOn;
        return this;
    }

    private boolean isLoggerOn(){
        return isLoggerOn;
    }

    private WebDriver getDriver() {
        return driver;
    }

    private DevTools getDevTools() {
        logger.debug("getting getDevTools");
        if (driver instanceof ChromeDriver) {
            if (devTools == null) devTools = ((ChromiumDriver) driver).getDevTools();
            return devTools;
        }
        else if (driver instanceof RemoteWebDriver) {
            switch (getCapabilities().getBrowserName()) {
                case "chrome":
                case "msedge":
                    if (devTools == null){
                        driver = new Augmenter().augment(driver);
                        devTools = ((HasDevTools) driver).getDevTools();
                    }
                    return devTools;
            }
        }
        throw new TestException("Currently devtools support only on chrome browser");
    }

}
