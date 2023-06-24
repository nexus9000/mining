package edu.itstep.blockchain;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MiningApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiningApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (String[] arg) -> {

		};
	}
}
