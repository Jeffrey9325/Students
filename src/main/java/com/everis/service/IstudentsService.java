package com.everis.service;

import com.everis.model.Students;

import java.util.Date;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IstudentsService {

  Flux<Students> searchbyName(String name);

  Mono<Students> searchbyDocument(String document);

  Flux<Students> searchbyrankdateofBirth(Date from, Date to);

  Mono<Students> createStudent(Students student);

  Flux<Students> allStudents();

  Mono<Students> modifyStudent(Students student);

  Mono<Void> deleteStudents(Students student);
  
  Mono<Students> findbyId(String id);
  
}