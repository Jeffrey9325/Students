package com.everis.controller;

import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerStudentTest {
	
  private static Logger LOG = LoggerFactory.getLogger(RestControllerStudentTest.class);

  @Autowired
  private WebTestClient webTestClient;

  @Autowired
  private ReactiveRepository repository;
  /**
   * unit test.
   */
  
  @Test
  public void searchbyName() {
    
    Students student = repository.findByFullName("jeffrey").blockFirst();
    if (student != null) {
      webTestClient.get()
           .uri("/Students/v1.0/names/{fullName}", Collections
           .singletonMap("fullName", student.getFullName()))
           .accept(MediaType.APPLICATION_JSON_UTF8)
           .exchange()
           .expectStatus().isOk()
           .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
           .expectBodyList(Students.class)
           .consumeWith(response -> {
             List<Students> studentList = response.getResponseBody();
             studentList.forEach(p -> {
               System.out.println(p.getId());
               System.out.println(p.getFullName());
               System.out.println(p.getGender());
               System.out.println(p.getDateofBirth());
               System.out.println(p.getTypeDocument());
               System.out.println(p.getDocumentNumber());
             });
           });
    }
  }
  /**
   * unit test.
   */
  
  @Test
  public void searchbyDocument() {

    Students student = repository.findByDocumentNumber("47704995").block();
    if (student != null) {
      webTestClient.get()
          .uri("/Students/v1.0/documents/{document}", Collections
          .singletonMap("document", student.getDocumentNumber()))
          .accept(MediaType.APPLICATION_JSON_UTF8)
          .exchange()
          .expectStatus().isOk()
          .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
          .expectBody(Students.class)
          .consumeWith(response -> {
            Assertions.assertThat(response.getResponseBody()).isNotNull();
          });
    }
  }

  /**
   * unit test.
   * @throws ParseException
   */
  
//  @Test
//  public void createStudent() throws ParseException {
//
//    Students student = new Students("1", "richard", "m", LocalDate.of(1993,02,25), "dni", "4770888");
//    webTestClient.post()
//        .uri("/Students/v1.0/")
//        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        .accept(MediaType.APPLICATION_JSON_UTF8)
//        .body(Mono.just(student), Students.class)
//        .exchange()
//        .expectStatus().isCreated();
//  }
  /**
   * unit test.
   */
  
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
          student.forEach(p -> {
        	  LOG.info(p.getId());
        	  LOG.info(p.getFullName());
        	  LOG.info(p.getGender());
        	  LOG.info(p.getTypeDocument());
        	  LOG.info(p.getDocumentNumber());
          });
        });
  }
  /**
   * unit test.
   * @throws ParseException
   */
  
  @Test
  public void updateStudent() throws ParseException {

    Students student = repository.findById("1").block();
    if (student != null) {
      Students newStudent = new Students(
          student.getId(), "Jeff", "m", LocalDate.of(1993,02,25), "dni", "159748");
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
  }
  /**
   * unit test.
   */
  
  @Test
  public void deleteStudents() {

    Students student = repository.findById("1").block();
    if (student != null) {
      webTestClient.delete()
        .uri("/Students/v1.0/{id}", Collections.singletonMap("id", student.getId()))
        .exchange()
        .expectStatus().isNoContent();
    }
  }
}
