package com.fravega.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableSwagger2
public class ChallengeApplication {
	private static final Logger LOGGER = Logger.getLogger( ChallengeApplication.class.getName() );

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "message 1");
		SpringApplication.run(ChallengeApplication.class, args);
	}

}
