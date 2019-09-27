package com.everis.controller;

import com.everis.model.Students;
import com.everis.service.StudentServiceImpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Students/v1.0")
public class RestControllerStudent {
	
	private static Logger LOG = LoggerFactory.getLogger(RestControllerStudent.class);
  /**
   * Student Service Implement.
   */
  @Autowired
  public StudentServiceImpl repository;
  /**
   * search by full name parents document.
   * @param fullName full name
   * @return
   */
  
  @GetMapping("/names/{fullName}")
  public Flux<Students> searchbyName(@PathVariable final String fullName) {
    return repository.searchbyName(fullName);
  }
  /**
   * search by identification document number student document.
   * @param document identification document number
   * @return
   */
  
  @GetMapping("/documents/{document}")
  public Mono<Students> searchbyDocument(@PathVariable final String document) {
    return repository.searchbyDocument(document);
  }
  /**
   * search by rank date of Birth student document.
   * @param fromDate date
   * @param toDate date
   * @return
   */
  
  @GetMapping("/dates/{fromDate}/{toDate}")
  public Flux<Students> searchbyrankdateofBirth(
      @PathVariable @DateTimeFormat(iso = ISO.DATE) final Date fromDate,
      @PathVariable  @DateTimeFormat(iso = ISO.DATE)  final Date toDate) {
    return repository.searchbyrankdateofBirth(fromDate, toDate);
  }
  /**
   * create record student document.
   * @param student student
   * @return
   */
  
  @PostMapping("/")
  public Mono<ResponseEntity<Students>> createStudent(@Valid @RequestBody final Students student) {
    return repository.createStudent(student)
    .then(Mono.just(new ResponseEntity<Students>(HttpStatus.CREATED)))
   
//    .switchIfEmpty(Mono.error(new ProductNotFoundException("vacio")))
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**
   * show all record of student document.
   * @return
   */
  
  //@HystrixCommand(fallbackMethod = "metodoalternativo")
  @GetMapping("/")
  public Flux<Students> allStudents() {  
    return repository.allStudents();
  }
  
//  
//	  String <Students> student = new Students("17", "pepito", "m", "dni", date, "14528985"); 
//	  student.flatMap(mapper)
//	  List<String> student =  Arrays.asList("17", "pepito", "m", "dni", date.toString(), "14528985"); 
//	  student.stream()
//	  

	
	  
//	  student.setId("17");
//	  student.setFullName("pepito");
//	  student.setGender("m");
//	  student.setTypeDocument("dni");
//	  student.setDateofBirth(date);
//	  student.setDocumentNumber("14528985");
  /**
   * modify record of student document.
   * @param id identification
   * @param student student
   * @return
   */
  
  @PutMapping("/{id}")
  public Mono<ResponseEntity<Students>> modifyStudent(@PathVariable final String id,
      @Valid @RequestBody final Students student) {
    return repository.findbyId(id)
    .flatMap(people -> {
      people.setId(id);
      people.setFullName(student.getFullName());
      people.setGender(student.getGender());
      people.setDateofBirth(student.getDateofBirth());
      people.setTypeDocument(student.getTypeDocument());
      people.setDocumentNumber(student.getDocumentNumber());
      return repository.modifyStudent(people);
    })
    .map(update -> new ResponseEntity<>(update, HttpStatus.OK))
    //.orElseThrow(() -> new ProductNotFoundException("vacio"))
    //.orElseThrow(Mono.error(new ProductNotFoundException("vacio")))
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  /**
   * delete record in student document.
   * @param id identification
   * @return
   */
 
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteStudents(@PathVariable final String id) {
    return repository.findbyId(id)
    .flatMap(people ->
    repository.deleteStudents(people)
    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))  
    )
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
   
}
