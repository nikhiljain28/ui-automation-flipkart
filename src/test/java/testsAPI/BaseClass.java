package testsAPI;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseClass {
	
	@BeforeClass
	public void baseSetup() {
		RestAssured.baseURI = "https://reqres.in";
	}
}
