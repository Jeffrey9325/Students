package com.everis.repository;

import com.everis.model.Students;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * ReactiveRepository interface.
 * @author jeffrey
 * @version v1.0
 */

@Repository
public interface ReactiveRepository extends ReactiveMongoRepository<Students, Serializable> {
  /**
   * find by Full Name student document.
   * @param fullName full name
   * @return
   */

  Flux<Students> findByFullName(String fullName);
  /**
   * find by identification document number student document.
   * @param documentNumber identification document number
   * @return
   */
  
  Mono<Students> findByDocumentNumber(String documentNumber);
  /**
   * find by rank date of birth student document.
   * @param fromDate date
   * @param toDate date
   * @return
   */
  
  Flux<Students> findByDateofBirthBetween(Date fromDate, Date toDate);
  /**
   * find by id student document.
   * @param idStudent id
   * @return
   */
  
  Mono<Students> findById(String idStudent);
  
}
