package com.everis.service;

import com.everis.model.Students;

import java.util.Date;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * IstudentsService interface.
 * @author jeffrey
 * @version v1.0
 */

public interface IstudentsService {
  /**
   * search by name student document.
   * @param name full name student
   * @return
   */

  Flux<Students> searchbyName(String name);
  /**
   * search by document student document.
   * @param document identification document number
   * @return
   */
  
  Mono<Students> searchbyDocument(String document);
  /**
   * search by rank date of birth.
   * @param fromDate date
   * @param toDate date
   * @return
   */
  
  Flux<Students> searchbyrankdateofBirth(Date fromDate, Date toDate);
  /**
   * create record student document.
   * @param student student
   * @return
   */
  
  Mono<Students> createStudent(Students student);
  /**
   * show all students of student document.
   * @return
   */
  
  Flux<Students> allStudents();
  /**
   * modify record student document.
   * @param student student
   * @return
   */
  
  Mono<Students> modifyStudent(Students student);
  /**
   * delete record of student document.
   * @param student student
   * @return
   */
  
  Mono<Void> deleteStudents(Students student);
  /**
   * find by id student document.
   * @param idStudent id
   * @return
   */
  
  Mono<Students> findbyId(String idStudent);
  
}