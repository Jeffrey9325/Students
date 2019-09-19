package com.everis.service;

import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * StudentServiceImpl class.
 * @author jeffrey
 * @version v1.0
 */

@Service
public class StudentServiceImpl implements IstudentsService {
  /**
   * Reactive Repository.
   */
  @Autowired
  private ReactiveRepository repository;
 
  
  @Override
  public Flux<Students> searchbyName(final String name) {
    return repository.findByFullName(name);
  }

  @Override
  public Mono<Students> searchbyDocument(final String document) {
    return repository.findByDocumentNumber(document);
  }

  @Override
  public Flux<Students> searchbyrankdateofBirth(final Date fromDate, final Date toDate) {
    return repository.findByDateofBirthBetween(fromDate, toDate);
  }

  @Override
  public Mono<Students> createStudent(final Students student) {
    return repository.save(student);
  }

  @Override
  public Flux<Students> allStudents() {
    return repository.findAll();
  }

  @Override
  public Mono<Students> modifyStudent(final Students student) {
    return repository.save(student);
  }

  @Override
  public Mono<Void> deleteStudents(final Students student) {
    return repository.delete(student);
  }

  @Override
  public Mono<Students> findbyId(final String idStudent) {
    return repository.findById(idStudent);
  }
}
