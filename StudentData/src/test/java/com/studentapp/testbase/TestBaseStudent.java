package com.studentapp.testbase;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class TestBaseStudent {
	@BeforeClass
	public static void init()
	{
		RestAssured.baseURI = "http://localhost:8080/student";
		
	}

}
