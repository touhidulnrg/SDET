import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
public class ExtentReportsClass{
 ExtentReports extent;
 ExtentTest logger;
  
 @BeforeTest
 public void startReport(){
 //ExtentReports(String filePath,Boolean replaceExisting)  
 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/ExtReport.html", true); 
 extent
         .addSystemInfo("Host Name", "SoftwareTestingMaterial")
         .addSystemInfo("Environment", "Automation Testing")
         .addSystemInfo("User Name", "Rajkumar SM");
  extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
 }
 
 @Test
 public void passTest(){
 //extent.startTest("TestCaseName", "Description")
 logger = extent.startTest("passTest");
 Assert.assertTrue(true);
 //To generate the log when the test case is passed
 logger.log(LogStatus.PASS, "Test Case Passed is passTest");
 }
 
 @Test
 public void failTest(){
 logger = extent.startTest("failTest");
 Assert.assertTrue(false);
 logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
 }
 
 @Test
 public void skipTest(){
 logger = extent.startTest("skipTest");
 throw new SkipException("Skipping - This is not ready for testing ");
 }
 
 @AfterMethod
 public void getResult(ITestResult result){
 if(result.getStatus() == ITestResult.FAILURE){
 	logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
 	logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
 }else if(result.getStatus() == ITestResult.SKIP){
 	logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
 }
 // ending test 
 extent.endTest(logger);
 }

 @AfterTest
 public void endReport(){
//flush() - to write or update test information to your report. 
     extent.flush();
 //Call close() at the very end of your session to clear all resources.     
extent.close();
    }
}
