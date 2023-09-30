package com.studentapp.junit.studentinfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.serenity.StudentSerenityTest;
import com.studentapp.testbase.TestBaseStudent;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent(threads="12x")
@UseTestDataFrom("testdata/student.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDrivenTest extends TestBaseStudent{
	@Steps
	StudentSerenityTest steps;
	
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private String course;
	
	public StudentSerenityTest getSteps() {
		return steps;
	}



	public void setSteps(StudentSerenityTest steps) {
		this.steps = steps;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getProgramme() {
		return programme;
	}



	public void setProgramme(String programme) {
		this.programme = programme;
	}



	public String getCourses() {
		return course;
	}



	public void setCourses(String courses) {
		this.course = courses;
	}



	
	
	
	@Title("DataDriver Test for adding multiplr students to the student app")
	@Test
	public void createMultipleStudent()
	{
		ArrayList<String> courses = new ArrayList<>();
		courses.add(course);
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
	}

}
