package com.everis.interfaces;

import com.everis.model.Students;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Istudents {

  Flux<Students> searchbyName(@PathVariable String name);

  Mono<Students> searchbyDocument(@PathVariable String document);

  Flux<Students> searchbyrankdateofBirth(@PathVariable @DateTimeFormat(iso = ISO.DATE) Date from,
      @PathVariable  @DateTimeFormat(iso = ISO.DATE) Date to);

  Mono<Students> createStudent(@RequestBody Students student);

  Flux<Students> allStudents();

  Mono<ResponseEntity<Students>> modifyStudent(@PathVariable String id, @RequestBody Students student);

  Mono<ResponseEntity<Void>> deleteStudents(@PathVariable String id);

}