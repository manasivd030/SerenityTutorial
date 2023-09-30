package com.studentapp.junit.studentinfo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenityTest;
import com.studentapp.testbase.TestBaseStudent;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtilsData;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentCRUDTest extends TestBaseStudent {


		static String firstName = "SMOKEUSER"+TestUtilsData.getRandomValue();
		static String lastName = "SMOKEUSER"+TestUtilsData.getRandomValue();
		static String email = TestUtilsData.getRandomValue()+"xyz@gmail.com";
		static String programme = "Computer Science";
		static int studentid;

		@Steps
		StudentSerenityTest steps;

		@Title("This test will create a new student")
		@Test
		public void test001()
		{
			ArrayList<String> courses = new ArrayList<String>();
			courses.add("JAVA");
			courses.add("C++");
			steps.createStudent(firstName, lastName, email, programme, courses)
			.statusCode(201)
			.spec(ReusableSpecifications.getGenericResponseSpec());


		}

		@Title("Verify if the Student was added to the application")
		@Test
		public void test002()
		{


			HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
			assertThat(value.equals(firstName));
			studentid = (int) value.get("id");
		}


		@Title("Update the user information and verify the user information")
		@Test
		public void test003()
		{
			String p1 = "findAll{it.firstName='}";
			String p2 = "'}.get(0)";
			ArrayList<String> courses = new ArrayList<String>();
			courses.add("JAVA");
			courses.add("C++");

			steps.updateStudent(studentid, firstName, lastName, email, programme, courses);

			HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);

			assertThat(value.equals(firstName));
			studentid = (int) value.get("id");
		}

		@Title("Delete the information of the student")
		@Test
		public void test004()
		{
			steps.deleteStudent(studentid);
			steps.getStudentbyId(studentid).statusCode(404);
		}

	}


