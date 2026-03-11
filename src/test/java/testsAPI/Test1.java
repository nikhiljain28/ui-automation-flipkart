package testsAPI;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.restassured.response.Response;

public class Test1 {
	
	final String s = "https://reqres.in";
	
	@Test
	public void getUsers() {
		Response response = given().header("x-api-key","reqres_a8ae2fbe32054c74891f7da507e56833").get("https://reqres.in/api/users");
		System.out.print(response.asPrettyString());
		System.out.println();
		System.out.println(response.getStatusCode());		
	}
	
	@Test
	public void checkingVariousData(ITestContext context) {
		baseURI = s;
		Response response = given().header("x-api-key","reqres_a8ae2fbe32054c74891f7da507e56833").queryParam("page", 2).
		when().get("/api/users");
		Map<String, Object> map = response.jsonPath().getMap("data[2]");
		for(Map.Entry<String, Object> object :map.entrySet()) {
			  System.out.print(object.getKey()+ ":"+ " ");
			    System.out.print(object.getValue());
			    System.out.println();
		}
		context.setAttribute("data-object", map);
	}
	
	@Test (dependsOnMethods = "checkingVariousData")
	public void checkingFirstName(ITestContext context) {
		baseURI = s;
		@SuppressWarnings("unchecked")
		Map<String,Object>map1 = (Map)context.getAttribute("data-object");
		System.out.println(map1);
	}
}
