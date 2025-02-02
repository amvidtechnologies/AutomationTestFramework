package webAutomation.elements.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;

@ImplementedBy(CustomWebElement.class)
public interface Element extends WebElement, WrapsElement, Locatable {
    boolean elementWired();

    String getDomAttribute();
}
