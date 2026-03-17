package constants;

import baseURI.BaseURIForAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.RequestPojo;
import utils.CustomLoggingFilter;

public class Users_EndPoints extends BaseURIForAPI{

//	public static final String user = BaseURIForAPI.baseURI+ "/user";
//	public static final String getUser = BaseURIForAPI.baseURI + "/user/{username}";
//	public static final String putUser = BaseURIForAPI.baseURI+ "/user/{username}";

	public static Response postUser(RequestPojo user) {
		Response response = RestAssured.given().filter(new CustomLoggingFilter()).spec(baseConifg()) 
				.body(user).when().post("/user");
		return response;
	}
	
	public static Response getUser(String username) {
		Response response = RestAssured.given().filter(new CustomLoggingFilter()).spec(baseConifg(username)).
				when().get("/user/{username}");		
		return response;
	}
	
	public static Response putUser(RequestPojo user, String userName) {
		Response response = RestAssured.given().filter(new CustomLoggingFilter()).spec(baseConifg(userName)).body(user)
				.when().put("/user/{username}");
		return response;
	}
}
