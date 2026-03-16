package constants;

import baseURI.BaseURIForAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.RequestPojo;

public class Users_EndPoints extends BaseURIForAPI{

	public static final String user = BaseURIForAPI.baseURI+ "/user";
	public static final String getUser = BaseURIForAPI.baseURI + "/user/{username}";
	public static final String putUser = BaseURIForAPI.baseURI+ "/user/{username}";

	public static Response postUser(RequestPojo user) {
		Response response = RestAssured.given().contentType(ContentType.JSON).
				accept(ContentType.JSON).body(user).when().post(Users_EndPoints.user);
		return response;
	}
	
	public static Response getUser(String username) {
		Response response = RestAssured.given().pathParam("username", username).
				when().get(Users_EndPoints.getUser);		
		return response;
	}
	
	public static Response putUser(RequestPojo user, String userName) {
		Response response = RestAssured.given().contentType(ContentType.JSON).
				accept(ContentType.JSON).pathParam("username", userName).body(user)
				.when().put(Users_EndPoints.putUser);
		return response;
	}
}
