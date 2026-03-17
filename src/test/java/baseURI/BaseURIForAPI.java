package baseURI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseURIForAPI {

	public static RequestSpecification baseConifg() {
		return new RequestSpecBuilder()
				.setBaseUri("https://petstore.swagger.io/v2")
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).build();
	}

	public static RequestSpecification baseConifg(String userName) {
		return new RequestSpecBuilder()
				.setBaseUri("https://petstore.swagger.io/v2")
				.addPathParam("username", userName)
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).build();
	}
	
	public static RequestSpecification securitySpe(String Token) {
		return new RequestSpecBuilder().addHeader("Authorization","Bearer"+Token).build();
	}

	public static RequestSpecification userFilterSpec(String status) {
		return new RequestSpecBuilder()
				.addQueryParam("status", status)
				.build();
	}
	
	//this will not be going to work because BaseURI is not present anywhere.
	
	public static RequestSpecification userPathSpec(String userName) {
		return new RequestSpecBuilder()
				.addPathParam("username", userName)
				.build();
	}
}
