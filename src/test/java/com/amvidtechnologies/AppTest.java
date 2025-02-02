package com.amvidtechnologies;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webAutomation.logger.LoggerManager;

public class AppTest{
    private static final Logger logger = LoggerManager.getLogger(AppTest.class);
    public static void main(String[] args) {

        System.out.println("Hello");
        WebDriverManager.chromedriver().win();
        logger.debug(AppTest.class);
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.close();
        driver.quit();
    }
}
