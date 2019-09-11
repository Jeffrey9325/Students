package com.everis.restcontroller;

import com.everis.interfaces.Istudents;
import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Students/v1.0")
public class RestControllerStudent implements Istudents {

  @Autowired
  private ReactiveRepository repository;

  @Override
  @RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
  public Flux<Students> searchbyName(String fullName) {
    return repository.findByfullName(fullName);
  }

  @Override
  @RequestMapping(value = "/documents/{document}", method = RequestMethod.GET)
  public Mono<Students> searchbyDocument(String document) {
    return repository.findByidentificationDocumentNumber(document);
  }

  @Override
  @RequestMapping(value = "/dates/{date}", method = RequestMethod.GET)
  public Flux<Students> searchbyrankdateofBirth(Date from, Date to) {
	//return repository.findByrankDate(date1, date2);
    return null;
  }

  @Override
  @RequestMapping(value = "/", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Students> createStudent(Students student) {
    return repository.save(student);
  }

  @Override
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public Flux<Students> allStudents() {
    return repository.findAll();
  }

  @Override
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Mono<Students> modifyStudent(String id, Students student) {
		//Students st = repository.findById(id);
    student.setId(id);
    return repository.save(student);
  }

  @Override
  @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteStudents(String id) {
    return repository.deleteById(id);
  }
}
