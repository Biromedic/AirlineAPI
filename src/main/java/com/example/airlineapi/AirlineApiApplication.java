package com.example.airlineapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class AirlineApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApiApplication.class, args);
	}

}
