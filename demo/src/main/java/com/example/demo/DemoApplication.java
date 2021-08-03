package com.example.demo;

import com.example.demo.infrastructure.RoleRepository;
import com.example.demo.model.Role;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class DemoApplication {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@Bean
	CommandLineRunner initDatabase (RoleRepository roleRepository){

		return args -> {
			log.info("loading role data" +roleRepository.save(new Role("DOCTOR")));
			log.info("loading role data" +roleRepository.save(new Role("PET_OWNER")));

		};
	}
}
