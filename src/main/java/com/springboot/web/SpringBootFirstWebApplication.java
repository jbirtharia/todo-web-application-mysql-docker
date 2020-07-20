package com.springboot.web;

import com.springboot.web.model.Todo;
import com.springboot.web.service.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringBootFirstWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootFirstWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstWebApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TodoRepository repository) {
		return args -> {
			repository.save(new Todo("jayendra", "Learn Spring Boot", java.sql.Date.valueOf(LocalDate.now()), false));
			repository.save(new Todo("jayendra", "Learn Angular JS", java.sql.Date.valueOf(LocalDate.now()), false));
			repository.save(new Todo("jayendra", "Learn to Dance", java.sql.Date.valueOf(LocalDate.now()), false));
		};
	}
}
