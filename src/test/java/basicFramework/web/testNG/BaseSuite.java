package basicFramework.web.testNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import webAutomation.basePages.BasePage;
import webAutomation.driver.CustomWebDriver;
import webAutomation.driver.DriverHandler;
import webAutomation.logger.LoggerManager;
import webAutomation.report.ExtentManager;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseSuite extends BaseSuiteVariables{
    private Logger logger = LoggerManager.getLogger(BaseSuite.class);
    protected CustomWebDriver driver;
    protected SoftAssert softAssert;
    private BasePage basePage;

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal();
    String reportName;

    public BaseSuite() {
        softAssert = new SoftAssert();
        reportName = reportName();
//        deleteFileDir("test-output");
    }

    @BeforeSuite()
    public void beforeSuite(){
        createFileDir("test-output/"+reportName);
        extent = ExtentManager.createInstance("test-output/"+reportName+"/extentReport.html");
    }

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browser){
        initiateVariable();

        driver = new CustomWebDriver(CustomWebDriver.BrowserTypes.valueOf(browser.toUpperCase()));
        DriverHandler.setDriver(driver);
        basePage = new BasePage(driver);
        basePage.openBrowserWithURL(getEnvURL());

//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.signIn("yimok39782@gexige.com","Pass@123");
        ExtentTest parent = extent.createTest(getClass().getSimpleName());
        parentTest.set(parent);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    @BeforeMethod
    public void beforeMethod(Method method){
        ExtentTest child = parentTest.get().createNode(method.getName());
        test.set(child);

    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        convertTestResult(result);
        extent.flush();
    }


    private void screenShot(String path,ITestResult result){
        try{
            // To create reference of TakesScreenshot And Call method to capture screenshot
            File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            // Copy files to specific location
            File dst = new File(path);
            // result.getName() will return name of test case so that screenshot name will be same as test case name
            FileUtils.copyFile(src, dst);
            logger.debug("Successfully Take Screenshot of FAILED test case: "+result.getName());
        }catch (Exception e){
            logger.warn("Failed to Take Screenshot of FAILED test case: "+result.getName());
        }
    }

    public String getCurrentDateAndTime(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
    private void initiateVariable(){
        setEnvURL();
    }
    private void convertTestResult(ITestResult result) {
        if ((result.getStatus() == ITestResult.SUCCESS)){
            test.get().pass("Test Passed");}
        else if ((result.getStatus() == ITestResult.FAILURE)){
            test.get().fail(result.getThrowable());
            String path = "test-output/"+reportName+"/screenshot/"+getClass().getSimpleName()+"_"+result.getName()+"_Fail.png";
            screenShot(path,result);
            try {
                test.get().addScreenCaptureFromPath("screenshot/"+getClass().getSimpleName()+"_"+result.getName()+"_Fail.png");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            test.get().skip(result.getThrowable());
        }
    }

    private void deleteFileDir(String deletePath){
        try {
            FileUtils.deleteDirectory(new File(deletePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void createFileDir(String createPath){
        try {
            FileUtils.createParentDirectories(new File(createPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
