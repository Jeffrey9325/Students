package com.everis;

import com.everis.model.Students;
import com.everis.repository.ReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class StudentMicroserviceApplication {

//	@Autowired
//	ReactiveRepository repository;

  private Logger log = LoggerFactory.getLogger(this.getClass());

  @Bean
 CommandLineRunner start(ReactiveRepository repository) {
    return args -> {

      Flux.just(
new Students("1","jeffrey","m","2019-02-17","dni","1234567"),
new Students("2","Jeffrey","m","2019-02-17","dni","1234567"))
.flatMap(repository::save)
        .subscribe(students -> log.info("Students: {}", students));
      
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(StudentMicroserviceApplication.class, args);
  }

}
