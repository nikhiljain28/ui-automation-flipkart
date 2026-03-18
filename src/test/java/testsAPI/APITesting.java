package testsAPI;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseURI.DataProviderforAPIS;
import constants.Users_EndPoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.RequestPojo;
import pojo.ResponsePojo;
import utils.ExtentReportManager;

public class APITesting extends DataProviderforAPIS {

	@Test(groups = "Mandatory",dataProvider = "userData", dataProviderClass = DataProviderforAPIS.class)
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

	@Test (groups = {"Mandatory","Regression"}, priority=2, dataProvider = "userNameData",dataProviderClass = DataProviderforAPIS.class)
	public void getUsers(String userName) {
		Response response = Users_EndPoints.getUser(userName);
		System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(groups = "Regression", priority = 3, dataProvider = "userData",dataProviderClass = DataProviderforAPIS.class)
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

	@Test (groups = "Mandatory", priority = 4, dataProvider = "userNameData",dataProviderClass = DataProviderforAPIS.class)
	public void validatingAttributesNotNull(String username) {
		Response response = Users_EndPoints.getUser(username);
		
		// Approach from the HashMap
		
//		HashMap<String, Object> map = response.getBody().as(HashMap.class);
//		for(Map.Entry<String, Object> entry : map.entrySet()) {
//		assertNotNull(entry.getValue(), entry.getKey()+ "Should not be null");
//		}
		
//		 Approach from THE Pojo Deserialization
		
		ResponsePojo response_Body = response.getBody().as(ResponsePojo.class);
		ResponsePojo user = new ResponsePojo(
				response_Body.getId(),
				response_Body.getUsername(),
				response_Body.getFirstName(),
				response_Body.getLastName(),
				response_Body.getEmail(),
				response_Body.getPassword(),
				response_Body.getPhone(),
				response_Body.getUserStatus());
		
		//Assertion on each value
		assertNotNull(user.getUsername(),  "username should not be null");
		assertNotNull(user.getFirstName(), "firstName should not be null");
		assertNotNull(user.getEmail(),     "email should not be null");
		assertNotNull(user.getPhone(),     "phone should not be null");
		assertNotNull(user.getId(), "id should not be null");
		
	}

}
