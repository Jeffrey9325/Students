package com.everis.interfaces;

import com.everis.model.Students;

import java.util.Date;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Istudents {

  Flux<Students> searchbyName(@PathVariable String name);

  Mono<Students> searchbyDocument(@PathVariable String document);

  Flux<Students> searchbyrankdateofBirth(@PathVariable Date from, @PathVariable Date to);

  Mono<Students> createStudent(@RequestBody Students student);

  Flux<Students> allStudents();

  Mono<Students> modifyStudent(@PathVariable String id, @RequestBody Students student);

  Mono<Void> deleteStudents(@PathVariable String id);

}