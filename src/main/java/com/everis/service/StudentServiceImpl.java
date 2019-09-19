package com.everis.service;

import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * 
 * @author Jeffrey
 *
 */
@Service
public class StudentServiceImpl implements IstudentsService {

  @Autowired
  ReactiveRepository repository;

  @Override
  public Flux<Students> searchbyName(String name) {
    return repository.findByFullName(name);
  }

  @Override
  public Mono<Students> searchbyDocument(String document) {
    return repository.findByIdentificationDocumentNumber(document);
  }

  @Override
  public Flux<Students> searchbyrankdateofBirth(Date from, Date to) {
    return repository.findByDateofBirthBetween(from, to);
  }

  @Override
  public Mono<Students> createStudent(Students student) {
    return repository.save(student);
  }

  @Override
  public Flux<Students> allStudents() {
    return repository.findAll();
  }

  @Override
  public Mono<Students> modifyStudent(Students student) {
    return repository.save(student);
  }

  @Override
  public Mono<Void> deleteStudents(Students student) {
    return repository.delete(student);
  }

  @Override
  public Mono<Students> findbyId(String id) {
    return repository.findById(id);
  }
}
