package com.example.finalSpringProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class })
public class FinalSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalSpringProjectApplication.class, args);
	}

}
