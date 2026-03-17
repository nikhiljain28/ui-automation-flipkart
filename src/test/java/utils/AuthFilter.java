package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AuthFilter implements Filter {

	    private String token;

	    public AuthFilter(String token) {
	        this.token = token;
	    }

	    @Override
	    public Response filter(FilterableRequestSpecification requestSpec,
	                           FilterableResponseSpecification responseSpec,
	                           FilterContext filterContext) {

	        // Add auth token to every request automatically
	        requestSpec.header("Authorization", "Bearer " + token);

	        // Proceed with modified request
	        return filterContext.next(requestSpec, responseSpec);
	    }
	}

