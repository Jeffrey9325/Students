package com.everis.repository;

import com.everis.model.Students;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveRepository extends ReactiveMongoRepository<Students, Serializable> {

  Flux<Students> findByFullName(String fullName);

  Mono<Students> findByIdentificationDocumentNumber(String identificationDocumentNumber);

  Flux<Students> findByDateofBirthBetween(Date from, Date to);

  Mono<Students> findById(String id);

}
