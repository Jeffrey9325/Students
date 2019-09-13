package com.everis.restcontroller;

import com.everis.interfaces.Istudents;
import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
  @RequestMapping(value = "/names/{fullName}", method = RequestMethod.GET)
  public Flux<Students> searchbyName(String fullName) {
    return repository.findByFullName(fullName);
  }

  @Override
  @RequestMapping(value = "/documents/{document}", method = RequestMethod.GET)
  public Mono<Students> searchbyDocument(String document) {
    return repository.findByIdentificationDocumentNumber(document);
  }

  @Override
  @RequestMapping(value = "/dates/{from}/{to}", method = RequestMethod.GET)
  public Flux<Students> searchbyrankdateofBirth(Date from, Date to) {
	return repository.findByDateofBirthBetween(from, to);
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
  @PutMapping("{id}")
  public Mono<ResponseEntity<Students>> modifyStudent(String id, Students student) {
    return repository.findById(id)
    		.flatMap(People -> {
    			People.setId(id);
    			People.setFullName(student.getFullName());
    			People.setGender(student.getGender());
    			People.setDateofBirth(student.getDateofBirth());
    			People.setTypeofIdentificationDocument(student.getTypeofIdentificationDocument());
    			People.setIdentificationDocumentNumber(student.getIdentificationDocumentNumber());
    			return repository.save(People);	
    		})
    		.map(update -> new ResponseEntity<>(update, HttpStatus.OK))
    		.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @Override
  @DeleteMapping("{id}")
  public Mono<ResponseEntity<Void>> deleteStudents(String id) {
	  return repository.findById(id)
			  .flatMap(People ->
				  repository.delete(People)
				  		.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))  
			  )
			  .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
