package webAutomation.driver;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import webAutomation.logger.LoggerManager;

import java.util.HashMap;
import java.util.Map;

public class DriverHandler {
    protected static Logger logger = LoggerManager.getLogger(DriverHandler.class);

    private final static Map<Integer, CustomWebDriver> driverContainer = new HashMap<>();
    private final static Map<Integer, DriverInfo> driverInfoContainer = new HashMap<>();

    private DriverHandler(){
    }

    public static synchronized void setDriver(CustomWebDriver driver){
        int currentThread = (int) Thread.currentThread().getId();
        driverContainer.put(currentThread, driver);
        logger.debug("setDriver: Current thread: "+currentThread+" Current driver: "+driver);
    }

    public static synchronized CustomWebDriver getDriver(){
        int currentThread = (int) Thread.currentThread().getId();
        CustomWebDriver driver = driverContainer.get(currentThread);
        logger.debug("getDriver: Current thread: "+currentThread+" Current driver: "+driver);
        return driver;
    }

    public static synchronized void removeDriver(){
        driverContainer.remove((int) Thread.currentThread().getId());
    }

    public static synchronized void setDriverInfo(){
        CustomWebDriver driver = getDriver();
        Capabilities capabilities = driver.getCapabilities();
        driverInfoContainer.put((int) Thread.currentThread().getId(),
            new DriverInfo(
                    capabilities.getBrowserName(),
                    capabilities.getBrowserVersion(),
                    driver.manage().window().getSize().toString()
            ));
    }

    public static synchronized DriverInfo getDriverInfo(){
        return driverInfoContainer.get((int) Thread.currentThread().getId());
    }

    public static Map <Integer, DriverInfo> getDriverInfoContainer(){
        return driverInfoContainer;
    }




}
