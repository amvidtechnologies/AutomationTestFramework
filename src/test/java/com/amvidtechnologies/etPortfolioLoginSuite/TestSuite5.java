package com.amvidtechnologies.etPortfolioLoginSuite;

import basicFramework.web.testNG.BaseSuite;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestSuite5 extends BaseSuite {

    @Test
    private void TestSuite5Test1(){
        System.out.println("Test1 in TestSuite5");
    }
    @Test
    private void TestSuite5Test2(){
        System.out.println("Test2 in TestSuite5");
    }
    @Test
    private void TestSuite5Test3(){
        Assert.fail();
        System.out.println("Test2 in TestSuite5");
    }
    @Test
    private void TestSuite5Test4(){
//        Assert.fail();
        System.out.println("Test2 in TestSuite5");
    }
    @Test
    private void TestSuite5Test5(){
        Assert.fail();
        System.out.println("Test2 in TestSuite5");
    }
}
