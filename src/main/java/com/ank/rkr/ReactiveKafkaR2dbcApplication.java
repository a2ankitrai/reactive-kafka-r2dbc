package com.ank.rkr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReactiveKafkaR2dbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveKafkaR2dbcApplication.class, args);
	}

	@RequestMapping(value = "/run")
	public String helloWorld() {
		return "Hello Ankit!";
	}

}
