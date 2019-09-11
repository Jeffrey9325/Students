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

  @Query("{ 'fullName': ?0 }")
  Flux<Students> findByfullName(String fullName);

  @Query("{ 'identificationDocumentNumber': ?0 }")
  Mono<Students> findByidentificationDocumentNumber(String identificationDocumentNumber);

	//@Query("select t from Students t where t. = ?1")
//	Flux<Students> findByrankDate(Date date1, Date date2 );

  Mono<Students> findById(String id);

}
