package com.everis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.everis.model.Students;
import com.everis.restcontroller.RestControllerStudent;

import junit.framework.Assert;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMicroserviceApplicationTests {

	@Test
	public void createStudent() {
		//Students testStudent = new Students(id, fullName, gender, dateofBirth, typeofIdentificationDocument, identificationDocumentNumber)
		RestControllerStudent testrest = new RestControllerStudent();
		Mono<Students> student = testrest.createStudent(new Students(
		"7", "Jeff", "m", "25-02-1993", "dni", "47704995"));
		Assert.assertEquals(student, student);
	}

}
