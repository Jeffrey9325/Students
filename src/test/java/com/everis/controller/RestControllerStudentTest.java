package com.everis.controller;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerStudentTest {

	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	ReactiveRepository repository;
	
	@Test
	public void searchbyName() {
		 Students show = repository.findByFullName("jeffrey").blockFirst();
		 webTestClient.get()
	                .uri("/Students/v1.0/names/{fullName}", Collections.singletonMap("fullName", show.getFullName()))
	                .accept(MediaType.APPLICATION_JSON_UTF8)
	                .exchange()
	                .expectStatus().isOk()
	                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
	                .expectBodyList(Students.class)
	                .consumeWith(response -> {
	                	List<Students> student = response.getResponseBody();
	        			student.forEach(p ->{
	        				System.out.println(p.getId());
	        				System.out.println(p.getFullName());
	        				System.out.println(p.getGender());
	        				System.out.println(p.getDateofBirth());
	        				System.out.println(p.getTypeofIdentificationDocument());
	        				System.out.println(p.getIdentificationDocumentNumber());
	        				
	        			});
	                
//	                    Students show = response.getResponseBody();
//	                    Assertions.assertThat(show.getFullName()).isNotEmpty();
//	                    Assertions.assertThat(show.getFullName().length()>0).isTrue();
//
	                });
	}
	
	@Test
	public void searchbyDocument() {
		
		Students student = repository.findByIdentificationDocumentNumber("47704995").block();
		webTestClient.get()
                .uri("/Students/v1.0/documents/{document}", Collections.singletonMap("document", student.getIdentificationDocumentNumber()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Students.class)
                .consumeWith(response -> {
                    Students show = response.getResponseBody();
                    Assertions.assertThat(show.getIdentificationDocumentNumber()).isNotEmpty();
                    Assertions.assertThat(show.getIdentificationDocumentNumber().length()>0).isTrue();

                });
	
	}
	
//	@Test
//	public void searchbyrankdateofBirth() {
//		
//		Students student = repository.findByDateofBirthBetween("", "").block();
//		webTestClient.get()
//                .uri("/Students/v1.0/dates/{from}/{to}", Collections.singletonMap("document", student.getIdentificationDocumentNumber()))
//                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
//                .expectBody(Students.class)
//                .consumeWith(response -> {
//                    Students show = response.getResponseBody();
//                    Assertions.assertThat(show.getIdentificationDocumentNumber()).isNotEmpty();
//                    Assertions.assertThat(show.getIdentificationDocumentNumber().length()>0).isTrue();
//
//                });
//	
//	}
	
	@Test
	public void createStudent() throws java.text.ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = sdf.parse("2019-09-16");
		Students student = new Students("1", "richard", "m", fecha, "dni", "4770888");
		
		webTestClient.post()
		.uri("/Students/v1.0/")
		.contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(student), Students.class)
		.exchange()
		.expectStatus().isCreated();
	}
	
	@Test
	public void allStudents() {
		
		webTestClient.get()
		.uri("/Students/v1.0/")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Students.class)
		.consumeWith(response -> {
			List<Students> student = response.getResponseBody();
			student.forEach(p ->{
				System.out.println(p.getId());
				System.out.println(p.getFullName());
				System.out.println(p.getGender());
				System.out.println(p.getDateofBirth());
				System.out.println(p.getTypeofIdentificationDocument());
				System.out.println(p.getIdentificationDocumentNumber());
				
			});
			
			//Assertions.assertThat(student.size()==6)
		});
		
	}
	
	@Test
	public void testUpdateStudent() throws ParseException {
		
		Students student = repository.findById("1").block();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = sdf.parse("2019-09-16");
        Students newStudent = new Students(student.getId(), "Jeff", "m", fecha, "dni", "159748");

        webTestClient.put()
        .uri("/Students/v1.0/{id}", Collections.singletonMap("id", student.getId()))
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(newStudent), Students.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.id").isEqualTo("1");
	}
	
	@Test
    public void deleteStudents(){
	
	    Students student = repository.findById("1").block();

	    webTestClient.delete()
                .uri("/Students/v1.0/{id}", Collections.singletonMap("id",  student.getId()))
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .isEmpty();
    }
	
}
