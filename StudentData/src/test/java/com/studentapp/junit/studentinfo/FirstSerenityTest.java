package com.studentapp.junit.studentinfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.*;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;


@RunWith(SerenityRunner.class)
public class FirstSerenityTest {
	
	@BeforeClass
	public static void init()
	{
		RestAssured.baseURI = "http://localhost:8080/student";
		
	}
	
	@Test
	public void getAllStudents()
	{
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	
	@Test
	public void thisIsAFailingTests()
	{
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(500);
		
	}
	
	@Pending
	@Test
	public void thisIsAPendingTests()
	{
		
	}
	
	@Ignore
	@Test
	public void thisIsASkippedTests()
	{
		
	}
	
	
	@Test
	public void thisIsATestWithError()
	{
		//Arithematic error
		System.out.println("This is an error"+(5/0));
		
	}
	
	@Test
	public void thisIsAFileNotFoundTest() throws FileNotFoundException
	{
		File file = new File("X://File.text");
		FileReader ff = new FileReader(file);
		
	}
	
	@Manual
	@Test
	public void thisIsAManualTest() 
	{
		
		
	}

}
