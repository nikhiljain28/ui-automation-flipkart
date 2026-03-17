package utils;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class CustomLoggingFilter implements Filter{

	@Override
	public Response filter(FilterableRequestSpecification requestSpec,
			FilterableResponseSpecification responseSpec,
			FilterContext filterContext) {

		// Log request BEFORE it is sent
		System.out.println("===== REQUEST =====");
		System.out.println("Method  : " + requestSpec.getMethod());
		System.out.println("URI     : " + requestSpec.getURI());
		System.out.println("Body    : " + requestSpec.getBody());
		System.out.println("Headers : " + requestSpec.getHeaders());

		// Proceed to next filter or send request
		Response response = filterContext.next(requestSpec, responseSpec);

		// Log response AFTER it is received
		System.out.println("===== RESPONSE =====");
		System.out.println("Status  : " + response.getStatusCode());
		System.out.println("Body    : " + response.asPrettyString());
		System.out.println("Time    : " + response.getTime() + "ms");

		return response;
	}
}


