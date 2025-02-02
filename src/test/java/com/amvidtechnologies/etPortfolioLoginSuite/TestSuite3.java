package com.amvidtechnologies.etPortfolioLoginSuite;

import basicFramework.web.testNG.BaseSuite;
import org.testng.annotations.*;

public class TestSuite3 extends BaseSuite {

    @Test
    private void TestSuite3Test1(){
//        Assert.fail();
        System.out.println("Test1 in TestSuite3");
    }
    @Test
    private void TestSuite3Test2(){
        System.out.println("Test2 in TestSuite3");
    }
}
