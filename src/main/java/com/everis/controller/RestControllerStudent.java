package com.everis.controller;

import com.everis.model.Students;
import com.everis.service.StudentServiceImpl;

import java.util.Date;
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

  @Autowired
  StudentServiceImpl repository;

  @GetMapping("/names/{fullName}")
  public Flux<Students> searchbyName(@PathVariable String fullName) {
    return repository.searchbyName(fullName);
  }

  @GetMapping("/documents/{document}")
  public Mono<Students> searchbyDocument(@PathVariable String document) {
    return repository.searchbyDocument(document);
  }

  @GetMapping("/dates/{from}/{to}")
  public Flux<Students> searchbyrankdateofBirth(
      @PathVariable @DateTimeFormat(iso = ISO.DATE) Date from,
      @PathVariable  @DateTimeFormat(iso = ISO.DATE)  Date to) {
    return repository.searchbyrankdateofBirth(from, to);
  }
  
  /**
   * 
   * @param student
   * @return 
   */
  @PostMapping("/")
  public Mono<ResponseEntity<Students>> createStudent(@RequestBody Students student) {
    return repository.createStudent(student)
    .then(Mono.just(new ResponseEntity<Students>(HttpStatus.CREATED)))
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/")
  public Flux<Students> allStudents() {
    return repository.allStudents();
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Students>> modifyStudent(@PathVariable String id,
      @RequestBody Students student) {
    return repository.findbyId(id)
    .flatMap(people -> {
      people.setId(id);
      people.setFullName(student.getFullName());
      people.setGender(student.getGender());
      people.setDateofBirth(student.getDateofBirth());
      people.setTypeofIdentificationDocument(student.getTypeofIdentificationDocument());
      people.setIdentificationDocumentNumber(student.getIdentificationDocumentNumber());
      return repository.modifyStudent(people);
    })
    .map(update -> new ResponseEntity<>(update, HttpStatus.OK))
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
 
  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteStudents(@PathVariable String id) {
    return repository.findbyId(id)
    .flatMap(people ->
    repository.deleteStudents(people)
    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))  
    )
    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
