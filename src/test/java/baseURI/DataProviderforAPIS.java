package baseURI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.RequestPojo;
import utils.CustomLoggingFilter;
import utils.EmailUtil;
import utils.ExcelDataReader;
import utils.ExtentReportManager;
import utils.LogUtil;
import utils.ResponseTimeFIlter;

public class DataProviderforAPIS {
	
	protected Response response;
	protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	
	@BeforeSuite
    public void setUpReport() {
        extent = ExtentReportManager.getInstance();
        RestAssured.filters(
                new CustomLoggingFilter(),
                new ResponseTimeFIlter()
            );
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        // Check if test FAILED
        if (result.getStatus() == ITestResult.FAILURE) {

            // Capture failure log with response details
            if (response != null) {
                LogUtil.captureFailureLog(response, result.getName());
            }

            System.out.println("Test Failed : " + result.getName());
            System.out.println("Reason      : " + result.getThrowable().getMessage());
        }

        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test Passed : " + result.getName());
        }
    }
    
    @AfterSuite
    public void tearDownReport() {

        //Flush — writes all logs to HTML file
        extent.flush();

        //Send report as email attachment
        EmailUtil.sendReportEmail(ExtentReportManager.reportPath);
        System.out.println("Report generated and emailed successfully!");
    }


	@DataProvider(name = "userData")
    public static Object[][] getUserData() throws Exception {
		// Most safe approach
		String filePath = System.getProperty("user.dir") 
		                + File.separator + "src"
		                + File.separator + "test"
		                + File.separator + "resources"
		                + File.separator + "UserTestData.xlsx";
        List<RequestPojo> users = ExcelDataReader.getUserData(
            filePath, "UserTestData"
        );

        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);   // Each row = one RequestPojo object
        }
        return data;
    }
	
	@DataProvider(name = "userNameData")
    public static Object[][] getUserNameData() throws Exception {
		// Most safe approach
		String filePath = System.getProperty("user.dir") 
		                + File.separator + "src"
		                + File.separator + "test"
		                + File.separator + "resources"
		                + File.separator + "UserTestData.xlsx";
        List<RequestPojo> users = ExcelDataReader.getUserData(
            filePath, "UserTestData"
        );
        List<String>userName = new ArrayList<>(users.size());
        for(int i=0;i<users.size();i++) {
        	userName.add(users.get(i).getUsername());
        }
        Object[][] data = new Object[userName.size()][1];
        for (int i = 0; i < userName.size(); i++) {
            data[i][0] = userName.get(i);   // Each row = one RequestPojo object
        }
        return data;
    }
}
