package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenityTest {
	
	@Step("Creating Student with firstName{0}, lastName:{1}, email:{2}, programme:{3}")
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, 
		List<String> courses)
	{

		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);


		return SerenityRest.rest().given()
		.spec(ReusableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then(); 
		
	}
	
	@Step("Getting the Student Information with firstname: {0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName)
	{
		String p1 = "findAll{it.firstName='}";
		String p2 = "'}.get(0)";

		return SerenityRest.rest().given()
				.when()
				.get("/list")
				.then()
				.log()
				.all()
				.statusCode(200)
				.extract()
				.path(p1+firstName+p2);
		
	}

	
	@Step("Updating Student information with studentid: {0,} firstName{1}, lastName:{2}, email:{3}, programme:{4}, courses:{5}")
	public ValidatableResponse updateStudent(int studentid, String firstName, String lastName, String email, String programme, 
		List<String> courses)
	{

		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);


		return SerenityRest.rest().given()
				.spec(ReusableSpecifications.getGenericRequestSpec())
				.when()
				.log()
				.all()
				.body(student)
				.put("/"+studentid)
				.then()
				.log()
				.all();
		
	}
	
	@Step("Deleting student information with Id: {0}")
	public void deleteStudent(int studentId)
	{
		SerenityRest.rest().given().when().delete("/"+studentId);
	}
	
	@Step("Get Student information using ID:{0}")
	public ValidatableResponse getStudentbyId(int studentId)
	{
		return SerenityRest.rest().given().when().get("/"+studentId).then();
	}
	
}
