package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ResponseTimeFIlter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec,
			FilterableResponseSpecification responseSpec,
			FilterContext filterContext) {

		// Record start time
		long startTime = System.currentTimeMillis();

		//Send request
		Response response = filterContext.next(requestSpec, responseSpec);

		// Calculate response time
		long endTime = System.currentTimeMillis();
		long responseTime = endTime - startTime;

		System.out.println("Response Time: " + responseTime + "ms");

		//Fail test if response time exceeds threshold
		if (responseTime > 2000) {
			System.out.println("WARNING: Response time exceeded 2 seconds!");
		}

		return response;
	}
}

