package webAutomation.elements.base;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import webAutomation.driver.CustomWebDriver;
import webAutomation.driver.DriverHandler;
import webAutomation.logger.LoggerManager;
import webAutomation.wait.CustomWebDriverWait;

public class CustomWebElement implements Element{

    protected Logger logger = LoggerManager.getLogger(CustomWebElement.class);
    protected final WebElement element;
    private final CustomWebDriverWait wait;
    private CustomWebDriver driver;
    public CustomWebElement(final WebElement element) {
        this(element,10,1000);
    }
    public CustomWebElement(final WebElement element,long timeOutInSeconds, long sleepInMillis) {
        this.element = element;
        driver = DriverHandler.getDriver();
        wait = new CustomWebDriverWait(driver, timeOutInSeconds,sleepInMillis);
    }

    @Override
    public boolean elementWired() {
        return (element != null);
    }

    @Override
    public void click() {
        String exceptionMessage;
        logger.debug("on element :{}", element);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            logger.debug("on element :{} - using selenium support ui", element);
            return;
        } catch (WebDriverException webDriverException) {
            exceptionMessage = webDriverException.getMessage();
            logger.warn("failed to click on element :{} - using selenium support ui", element);
        }

        try {
            element.click();
            logger.debug("on element :{} - using selenium", element);
            return;
        } catch (WebDriverException webDriverException) {
            logger.warn("failed to click on element :{} - using selenium", element);
        }

        try {
            new Actions(driver)
                    .click(element)
                    .build()
                    .perform();
            logger.debug("on element :{} - using Actions", element);
        } catch (WebDriverException webDriverException) {
            logger.warn("failed to click on element :{} - using Actions", element);
        }

        try {
            driver.executeScript("arguments[0].click();", element);
            logger.debug("click on element :{} using java script", element);
            return;
        }catch (WebDriverException webDriverException) {
            logger.warn("failed to click on element :{} - using java script", element);
        }

        throw new WebDriverException(exceptionMessage);
    }

    @Override
    public void submit() {
        logger.debug(element.toString());
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        String exceptionMessage;
        logger.debug("on element :{} "+"sending Keys: "+ Arrays.toString(keysToSend), element);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(keysToSend);
            logger.debug("on element :{} - using selenium support ui element To Be Clickable", element);
            return;
        } catch (WebDriverException webDriverException) {
            exceptionMessage = webDriverException.getMessage();
            logger.warn("failed to click on element :{} - using selenium support ui element To Be Clickable", element);
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(keysToSend);
            logger.debug("on element :{} - using selenium support ui visibility Of element", element);
            return;
        } catch (WebDriverException webDriverException) {
            exceptionMessage = webDriverException.getMessage();
            logger.warn("failed to click on element :{} - using selenium support ui visibility Of element", element);
        }

        try {
            element.sendKeys(keysToSend);
            logger.debug("on element :{} - using selenium", element);
            return;
        } catch (WebDriverException webDriverException) {
            logger.warn("failed to click on element :{} - using selenium", element);
        }

        try {
            new Actions(driver)
                    .sendKeys(element, keysToSend)
                    .build()
                    .perform();
            logger.debug("on element :{} - using Actions", element);
        } catch (WebDriverException webDriverException) {
            logger.warn("failed to send keys on element :{} - using Actions", element);
        }

        try {
            driver.executeScript("arguments[0].setAttribute('value', '" + keysToSend +"')", element);
            logger.debug("click on element :{} using java script", element);
            return;
        }catch (WebDriverException webDriverException) {
            logger.warn("failed to click on element :{} - using java script", element);
        }

        throw new WebDriverException(exceptionMessage);
    }

    @Override
    public void clear() {
        logger.debug(element.toString());
        element.clear();
    }

    @Override
    public String getTagName() {
        String elementTagName = element.getTagName();
        logger.debug("on element : {} - tag name {}", element, elementTagName);
        return elementTagName;
    }

    @Override
    public String getAttribute(String name) {
        String elementAttribute = element.getAttribute(name);
        logger.debug("on element : {} - tag name {}", element, elementAttribute);
        return elementAttribute;
    }

    @Override
    public String getAriaRole() {
        String ariaRole = this.element.getAriaRole();
        logger.debug("on element : {} - getAriaRole() - {} ",element,ariaRole);
        return ariaRole;
    }

    @Override
    public String getAccessibleName() {
        String accessibleName = this.element.getAccessibleName();
        logger.debug("on element : {} - getAccessibleName() - {} ",element,accessibleName);
        return accessibleName;
    }

    @Override
    public boolean isSelected() {
        boolean elementIsSelected = element.isSelected();
        logger.debug("on element : {} - is selected ? - {}",element,elementIsSelected);
        return elementIsSelected;
    }

    @Override
    public boolean isEnabled() {
        boolean elementIsEnabled = element.isEnabled();
        logger.debug("on element : {} - is enabled ? - {}",element,elementIsEnabled);
        return elementIsEnabled;
    }

    @Override
    public String getText() {
        String elementText;
        if (element.isDisplayed()) {
            elementText = element.getText();
            logger.debug("using selenium - element : {} , text : '{}'", element, elementText);
        } else {
            elementText = element.getAttribute("textContent")
                    .replaceAll("\\s+", " ")
                    .trim();
            logger.warn("Using getAttribute(textContent) - element : {} , text : '{}'", element, elementText);
        }
        return elementText;
    }

    @Override
    public List<WebElement> findElements(By by) {
        logger.debug(by.toString());
        List<WebElement> elementsImpl = new ArrayList<>();
        element.findElements(by).forEach(element -> elementsImpl.add(new CustomWebElement(element)));
        return elementsImpl;
    }

    @Override
    public WebElement findElement(By by) {
        return new CustomWebElement(element.findElement(by));
    }

    @Override
    public SearchContext getShadowRoot() {
        SearchContext shadowRoot = this.element.getShadowRoot();
        logger.debug("on element : {} - getShadowRoot() - {} ",element,shadowRoot);
        return shadowRoot;
    }

    @Override
    public boolean isDisplayed() {
        boolean elementIsDisplayed = element.isDisplayed();
        logger.debug("on element : {} - is displayed ? - {}",element,elementIsDisplayed);
        return elementIsDisplayed;
    }

    @Override
    public Point getLocation() {
        Point elementLocation = element.getLocation();
        logger.debug("on element : {} - element location - {}",element,elementLocation);
        return elementLocation;
    }

    @Override
    public Dimension getSize() {
        Dimension elementSize = element.getSize();
        logger.debug("on element : {} - element size - {}",element,elementSize);
        return elementSize;
    }

    @Override
    public Rectangle getRect() {
        Rectangle elementRect = element.getRect();
        logger.debug("on element : {} - element rectangle - {}",element, " { point :"+elementRect.getPoint()+" , dimension : "+elementRect.getDimension()+" }");
        return elementRect;
    }

    @Override
    public String getCssValue(String propertyName) {
        String elementCssValue = element.getCssValue(propertyName);
        logger.debug("({}) on element : {} - element rectangle - {}",propertyName,element,elementCssValue);
        return elementCssValue;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        logger.debug("({}) on element : {}", target, element);
        return element.getScreenshotAs(target);
    }

    @Override
    public WebElement getWrappedElement() {
        return this.element;
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable)element).getCoordinates();
    }

    @Override
    public String getDomProperty(String name) {
        String domProperty = element.getDomProperty(name);
        logger.debug("on element : {} - getDomProperty() - {}", element, name);
        return domProperty;
    }

    @Override
    public String getDomAttribute(String name) {
        String domAttribute = element.getDomAttribute(name);
        logger.debug("on element : {} - getDomAttribute() - {}", element, name);
        return domAttribute;
    }

    @Override
    public String getDomAttribute() {
        return null;
    }
}
