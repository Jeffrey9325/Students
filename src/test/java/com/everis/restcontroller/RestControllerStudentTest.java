package com.everis.restcontroller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;

@ExtendWith(SpringExtension.class)
class RestControllerStudentTest {
	
	@Mock
	RestControllerStudent restcontrollerstudent;
	
    private WebTestClient client;
    private List<Students> expectedProducts;
    
    //aqui
    @MockBean
    private ReactiveRepository repository;
//
//    Students uno = new Students("1", "jeffrey", "m", "2019-01-01", "dni", "47704995");
//    Students dos = new Students("2", "jeffrey", "m", "2019-01-01", "dni", "47704995");
    
//    @BeforeEach
//    void setUp() {
//        client = WebTestClient
//                .bindToController(new RestControllerStudent(restcontrollerstudent))
//                .configureClient()
//                .baseUrl("/Students/v1.0/")
//                .build();
//
//        expectedProducts = Arrays.asList(
//                Students.builder().id("1").fullName("al").gender("a").dateOfBirth("22").typeDocument("a").document("d").idStudent("1").build(),
//                Students.builder().id("2").fullName("al").gender("a").dateOfBirth("22").typeDocument("a").document("d").idStudent("1").build());
//
//    }

	@Test
	void test() {
		fail("Not yet implemented");
	}
;
	
	
	@Test
	void allStudents() {
		
	}
	
	
	@Test
	public void createStudent() {

	}

}
