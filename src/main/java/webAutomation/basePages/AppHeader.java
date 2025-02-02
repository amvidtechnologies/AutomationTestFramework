package webAutomation.basePages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webAutomation.driver.CustomWebDriver;

public class AppHeader extends BasePage {
    public AppHeader(CustomWebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//header")
    WebElement headerBar;
    @FindBy(xpath = "//a[@href='/pw_login.cms']")
    WebElement headerLogo;
    @FindBy(xpath = "//span[text()='Choose Language']//following::option")
    WebElement chooseLanguage;
    @FindBy(xpath = "//ul[@role='navigation']//child::a")
    WebElement faqLink;

    public boolean isHeaderDisplay(){
        return element(headerBar).isDisplayed();
    }
    public boolean isLogoDisplay(){
        return element(headerLogo).isDisplayed();
    }
    public String getChooseLanguage(){
        return element(chooseLanguage).getText();
    }
    public String getFAQLink(){
        return element(faqLink).getAttribute("href");
    }

}
