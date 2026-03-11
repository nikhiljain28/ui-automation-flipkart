package testsAPI;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class PostRequest extends BaseClass {
	
	private static int global_id;
	
	public static void setter(int id) {
		global_id = id; 
	}
	
	public RequestSpecification req(String name, String jobTitle) {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("job", jobTitle);
		RequestSpecification req1 = RestAssured.given()
				 .header("x-api-key","reqres_ebf46aa60996457abf920787feb13a3a")
				 .header("Content-Type","application/json")
				 .body(json.toString());
				return req1;
	}

	public RequestSpecification req1(String name, String jobTitle) {
		JSONObject json = new JSONObject();
		json.put("name", name);
		json.put("job", jobTitle);
		RequestSpecification re1 = RestAssured.given()
				.pathParam("id", global_id)
				 .header("x-api-key","reqres_ebf46aa60996457abf920787feb13a3a")
				 .header("Content-Type","application/json")
				 .body(json.toString());
				return re1;
	}
	@Test
	public void sendingPostRequest() {
	Response rem = req("Aditya Jain","Senior Developer")
		.when().post("/api/users/2");
	int id = Integer.parseInt(rem.jsonPath().getString("id"));
	setter(id);
//	Assert.assertTrue(id>0 && id<50);
	Assert.assertEquals(id, 47);
	}

	@Test
	public void changeUsingPutRequest() {
		req1("Nikhil Jain","Quality Engineer").
		when().put("/api/users/{id}")
		.then().statusCode(200).log().all();
		
	}
}
