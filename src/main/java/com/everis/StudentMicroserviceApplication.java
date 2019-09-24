package com.everis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;
/**
 * StudentMicroserviceApplication class.
 * @author jeffrey
 * @version v1.0
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2WebFlux
public class StudentMicroserviceApplication {
  /**
   * main method.
   * @param args arguments.
   */
  public static void main(final String[] args) {

    SpringApplication.run(StudentMicroserviceApplication.class, args);
    
  }

}
