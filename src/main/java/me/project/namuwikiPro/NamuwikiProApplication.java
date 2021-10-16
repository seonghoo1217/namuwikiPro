package me.project.namuwikiPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NamuwikiProApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamuwikiProApplication.class, args);
	}

}
