package com.amvidtechnologies.rahulShettyAcademy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webAutomation.logger.LoggerManager;

import java.time.Duration;

public class locatorPractice {

    private final static Logger logger = LoggerManager.getLogger(locatorPractice.class);

    public static void main(String[] args) throws InterruptedException {
        locatorPractice practice = new locatorPractice();
        practice.login();
    }


    public void login() throws InterruptedException {

        WebDriverManager.chromedriver().win();
        //System.setProperty("webdriver.chrome.driver","path");
        WebDriver driver = new ChromeDriver();
        logger.debug("new driver of chrome driver created successfully");
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Forgot your password?']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Go to Login']"))).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='submit signInBtn']"))).click();



        Thread.sleep(3000);
        driver.quit();

    }



}
