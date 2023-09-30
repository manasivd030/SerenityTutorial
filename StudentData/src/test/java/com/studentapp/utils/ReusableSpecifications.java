package com.studentapp.utils;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder rspec;
	public static RequestSpecification requestspecification;
	
	public static ResponseSpecBuilder resspec;
	public static ResponseSpecification responsespecification;
	
	public static RequestSpecification getGenericRequestSpec()
	{
		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON);
		requestspecification = rspec.build();
		return requestspecification;
	}
	
	public static ResponseSpecification getGenericResponseSpec()
	{
		resspec = new ResponseSpecBuilder();
		resspec.expectHeader("Content-Type", "application/json;charset=UTF-8");
		resspec.expectHeader("Transfer-Encoding","chunked");
		resspec.expectResponseTime(Matchers.lessThan(5000L),TimeUnit.SECONDS);
		responsespecification = resspec.build();
		return responsespecification;
		
	}

}
