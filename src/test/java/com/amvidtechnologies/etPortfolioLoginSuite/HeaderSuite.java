package com.amvidtechnologies.etPortfolioLoginSuite;

import basicFramework.web.testNG.BaseSuite;
import org.testng.annotations.*;
import webAutomation.basePages.AppHeader;
import webAutomation.loginPage.LoginPage;

public class HeaderSuite extends BaseSuite {
    AppHeader appHeader;
    LoginPage loginPage;
    @Test
    private void test1() throws InterruptedException {
        appHeader = new AppHeader(driver);
        softAssert.assertTrue(appHeader.isHeaderDisplay(),"Header is not displayed");
        softAssert.assertTrue(appHeader.isLogoDisplay(),"Header Logo is not displayed");
        softAssert.assertTrue(appHeader.getChooseLanguage().equals("English"),"Header Language is not English");
        softAssert.assertTrue(appHeader.getFAQLink().equals("http://support.etportfolio.economictimes.indiatimes.com/"),"Header FAQ link is not present.");
    }
}
