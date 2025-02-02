package webAutomation.loginPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webAutomation.basePages.BasePage;
import webAutomation.driver.CustomWebDriver;


public class LoginPage extends BasePage {

    public LoginPage(CustomWebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[normalize-space()='SIGN IN']")
    WebElement signInBtn;
    @FindBy(xpath = "//span[contains(text(),'Sign in using Email id')]")
    WebElement signInUsingEmail;
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailTextBox;
    @FindBy(xpath = "//input[@onclick='objUserLogin.loginIdCheck();']")
    WebElement continueBtn1;
    @FindBy(xpath = "//input[@onclick='objUserLogin.login();']")
    WebElement continueBtn2;
    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordTextBox;

    public void signIn(String email, String password){
       element(signInBtn).click();
       element(signInUsingEmail).click();
       element(emailTextBox).sendKeys(email);
       element(continueBtn1).click();
       element(passwordTextBox).sendKeys(password);
       element(continueBtn2).click();
    }
}
