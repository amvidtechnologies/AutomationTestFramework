package webAutomation.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.Logs;
import webAutomation.logger.LoggerManager;

import java.util.Set;

public class CustomOptions implements WebDriver.Options {

    protected Logger logger = LoggerManager.getLogger(CustomOptions.class);
    private WebDriver driver;
    private CustomTimeouts customTimeouts;
    private CustomWindow customWindow;

    public CustomOptions(WebDriver driver){
        this.driver=driver;
    }

    @Override
    public void addCookie(Cookie cookie) {
        logger.debug("driver manage add cookie: "+cookie.toString());
        driver.manage().addCookie(cookie);
    }

    @Override
    public void deleteCookieNamed(String name) {
        logger.debug("driver manage delete cookie Named: "+name);
        driver.manage().deleteCookieNamed(name);
    }

    @Override
    public void deleteCookie(Cookie cookie) {
        logger.debug("driver manage delete cookie: "+cookie.toString());
        driver.manage().deleteCookie(cookie);
    }

    @Override
    public void deleteAllCookies() {
        logger.debug("driver manage delete All Cookie");
        driver.manage().deleteAllCookies();
    }

    @Override
    public Set<Cookie> getCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        logger.debug("driver manage get cookies: "+cookies.toString());
        return cookies;
    }

    @Override
    public Cookie getCookieNamed(String name) {
        Cookie cookie = driver.manage().getCookieNamed(name);
        logger.debug("driver manage get cookie by named: "+cookie.toString());
        return cookie;
    }

    @Override
    public WebDriver.Timeouts timeouts() {
        if(customTimeouts == null){
            customTimeouts = new CustomTimeouts(driver);
        }
        return customTimeouts;
    }

    @Override
    public WebDriver.Window window() {
        if(customWindow == null){
            customWindow = new CustomWindow(driver);
        }
        return customWindow;
    }

    @Override
    public Logs logs() {
        Logs logs = driver.manage().logs();
        logger.debug("driver manage logs "+logs.toString());
        return logs;
    }
}
