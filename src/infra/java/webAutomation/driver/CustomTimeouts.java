package webAutomation.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import webAutomation.logger.LoggerManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CustomTimeouts implements WebDriver.Timeouts {
    protected Logger logger = LoggerManager.getLogger(CustomTimeouts.class);
    private WebDriver driver;

    public CustomTimeouts(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver.Timeouts implicitlyWait(long time, TimeUnit unit) {
        logger.debug("wait time = " + time + unit.toString());
        driver.manage().timeouts().implicitlyWait(time, unit);
        return this;
    }

    public WebDriver.Timeouts implicitlyWait(Duration duration) {
        logger.debug("driver manage timeouts implicitlyWait time " + duration);
        driver.manage().timeouts().implicitlyWait(duration);
        return this;
    }

    @Override
    public WebDriver.Timeouts setScriptTimeout(long time, TimeUnit unit) {
        logger.debug(" wait this = ", +time + unit.toString());
        driver.manage().timeouts().setScriptTimeout(time, unit);
        return this;
    }

    @Override
    public WebDriver.Timeouts setScriptTimeout(Duration duration) {
        logger.debug(" wait this = " + duration.toString());
        driver.manage().timeouts().setScriptTimeout(duration);
        return this;
    }

    @Override
    public WebDriver.Timeouts scriptTimeout(Duration duration) {
        logger.debug(" = {} ", duration.toString());
        driver.manage().timeouts().scriptTimeout(duration);
        return this;
    }

    @Override
    public Duration getScriptTimeout() {
        Duration scriptTimeout = driver.manage().timeouts().getScriptTimeout();
        logger.debug(" = {} ", scriptTimeout);
        return scriptTimeout;
    }

    @Override
    public WebDriver.Timeouts pageLoadTimeout(long time, TimeUnit unit) {
        logger.debug(" Page Load Timeout " + time + unit.toString());
        driver.manage().timeouts().pageLoadTimeout(time, unit);
        return this;
    }

    @Override
    public WebDriver.Timeouts pageLoadTimeout(Duration duration) {
        logger.debug(" Page Load Timeout " + duration);
        driver.manage().timeouts().pageLoadTimeout(duration);
        return this;
    }

    @Override
    public Duration getPageLoadTimeout() {
        Duration pageLoadTimeout = driver.manage().timeouts().getPageLoadTimeout();
        logger.debug(" Get Page Load Timeout " + pageLoadTimeout);
        return pageLoadTimeout;
    }
}
