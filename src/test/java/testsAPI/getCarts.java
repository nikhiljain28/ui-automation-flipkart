package testsAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class getCarts {


	@Test
	void getAllTheDataFromCart() throws JSONException {
		baseURI = "https://dummyjson.com";
		Response response = 
				given()
				.contentType("application/json")                    
				.when()
				.get("/carts")                          // endpoint
				.then()                        
				.statusCode(200)                        // validate response code
				.extract()
				.response();     
		String responseBody = response.getBody().asString().trim();
		//		System.out.println("RAW: " + responseBody);
		ArrayList<Float>list1 = new ArrayList<>();
		JSONObject jsonObject = new JSONObject(responseBody);
		for(int i=0; i<jsonObject.getJSONArray("carts").length();i++) {
			JSONArray jsonArray = jsonObject.getJSONArray("carts");
			for(int j=0; j<jsonArray.getJSONObject(i).getJSONArray("products").length();j++) {
				float price = jsonObject.getJSONArray("carts").getJSONObject(i).getJSONArray("products").getJSONObject(j).getFloat("price");
				list1.add(price);
			}
		}
		int count=0;
		for(int i=0;i<list1.size();i++) {
			System.out.println(list1.get(i));
			if(list1.get(i)>1000) {
				count=count+1;
			}
		}
		System.out.println(count);
	}
}
