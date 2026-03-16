package testsAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseURI.DataProviderforAPIS;
import constants.Users_EndPoints;
import io.restassured.response.Response;
import pojo.RequestPojo;
import utils.ExtentReportManager;

public class APITesting extends DataProviderforAPIS {

	@Test(dataProvider = "userData", dataProviderClass = DataProviderforAPIS.class)
	public void createANewUser(RequestPojo request) throws Exception {
		test.set(ExtentReportManager.getInstance()
                .createTest("Create User — " + request.getUsername()));
		test.get().log(Status.INFO, "Sending POST request for : " + request.getUsername());
		Response response = Users_EndPoints.postUser(request);
		test.get().log(Status.INFO, "Status Code : " + response.getStatusCode());
        test.get().log(Status.INFO, "Response    : " + response.asPrettyString());
		System.out.print(response.asString());
		System.out.println();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test (priority =2, dependsOnMethods = "createANewUser", dataProvider = "userNameData",dataProviderClass = DataProviderforAPIS.class)
	public void getUsers(String userName) {
		Response response = Users_EndPoints.getUser(userName);
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 3, dataProvider = "userData",dataProviderClass = DataProviderforAPIS.class)
	public void updateUser(RequestPojo request) {
		test.set(ExtentReportManager.getInstance()
                .createTest("Update User — " + request.getUsername()));

		String userName = request.getUsername();
		request.setFirstName(request.getUsername()+"wwwe");
		test.get().log(Status.INFO, "Sending PUT request for : " + userName);
		Response response = Users_EndPoints.putUser(request, userName);
		test.get().log(Status.INFO, "Status Code : " + response.getStatusCode());
        test.get().log(Status.INFO, "Response    : " + response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
