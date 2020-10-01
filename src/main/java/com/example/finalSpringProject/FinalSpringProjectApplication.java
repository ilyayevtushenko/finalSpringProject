package com.example.finalSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class FinalSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalSpringProjectApplication.class, args);
	}

}
