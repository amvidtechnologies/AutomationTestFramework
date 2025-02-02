package webAutomation.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import webAutomation.logger.LoggerManager;

public class CustomWindow implements WebDriver.Window {
    protected Logger logger = LoggerManager.getLogger(CustomWindow.class);
    private WebDriver driver;

    public CustomWindow (WebDriver driver){
        this.driver = driver;
    }
    @Override
    public Dimension getSize() {
        Dimension size = driver.manage().window().getSize();
        logger.debug("driver manage window get size: "+size.toString());
        return size;
    }

    @Override
    public void setSize(Dimension targetSize) {
        logger.debug("driver manage window set size: "+targetSize.toString());
        driver.manage().window().setSize(targetSize);
    }

    @Override
    public Point getPosition() {
        Point position = driver.manage().window().getPosition();
        logger.debug("driver manage window get position: "+position.toString());
        return position;
    }

    @Override
    public void setPosition(Point targetPosition) {
        logger.debug("driver manage window set position: "+targetPosition.toString());
        driver.manage().window().setPosition(targetPosition);
    }

    @Override
    public void maximize() {
        logger.debug("driver manage window maximize");
        driver.manage().window().maximize();
    }

    @Override
    public void minimize() {
        logger.debug("driver manage window minimize");
        driver.manage().window().minimize();
    }

    @Override
    public void fullscreen() {
        logger.debug("driver manage window full screen");
        driver.manage().window().fullscreen();
    }
}
