package webAutomation.handler;

import webAutomation.driver.CustomWebDriver;
import webAutomation.exception.TestException;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webAutomation.logger.LoggerManager;
import webAutomation.wait.CustomWebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class WindowHandler {
    protected Logger logger = LoggerManager.getLogger(WindowHandler.class);
    private CustomWebDriver driver;
    private String defaultWindow;
    private CustomWebDriverWait webDriverWait;

    public WindowHandler(CustomWebDriver driver) {
        this.driver = driver;
        webDriverWait = new CustomWebDriverWait(driver, 15,1000);
        setDefaultWindow();
    }

    public List<String> getWindowList() {
        return new ArrayList<>(driver.getWindowHandles());
    }

    public void setDefaultWindow(){
        this.defaultWindow = driver.getWindowHandle();
    }
    public void switchWindowToDefault(){
        driver.switchTo().window(defaultWindow);
    }
    public void switchWindowByIndex(int index){
        driver.switchTo().window(getWindowList().get(index));
    }
    public void switchWindowByIndex(int index, int ExpectedNumberOfWindow){
        webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(ExpectedNumberOfWindow));
        driver.switchTo().window(getWindowList().get(index));
    }
    public void switchWindowByTitle(String title){
        for (String window : getWindowList()) {
            driver.switchTo().window(window);
            if(webDriverWait.until(ExpectedConditions.titleContains(title))){
                return;
            }
        }
        throw new TestException("window not found with title :" + title);
    }
    public void switchWindowByTitle(String title, int ExpectedNumberOfWindow) {
        webDriverWait.until(ExpectedConditions.numberOfWindowsToBe(ExpectedNumberOfWindow));
        for (String window : getWindowList()) {
            driver.switchTo().window(window);
            try {
                webDriverWait.until(ExpectedConditions.titleContains(title));
                return;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public void closeAllWindowsExceptDefault() {
        List<String> allWindows = getWindowList();
        allWindows.forEach(window -> {
            if (!window.equals(defaultWindow)){
                try {
                    driver.switchTo().window(window).close();
                    logger.debug("window {} closed with selenium", window);
                }catch (Exception e){
                    logger.debug("failed to close window with selenium");
                }
                try {
                    driver.switchTo().window(window);
                    driver.executeScript("window.close()");
                }catch (Exception e){
                    logger.debug("failed to close window with javascript");
                }
            }
        });
        driver.switchTo().window(defaultWindow);
    }
    public void closeCurrentAndSwitchWindow(int index){
        driver.close();
        switchWindowByIndex(index);
    }
    public void closeCurrentAndSwitchDefault(){
        driver.close();
        switchWindowToDefault();
    }
    public void closeCurrentAndSwitchWindow(String title) throws Exception {
        driver.close();
        switchWindowByTitle(title);
    }
}