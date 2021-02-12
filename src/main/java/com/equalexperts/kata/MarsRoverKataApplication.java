package com.equalexperts.kata;

import com.equalexperts.kata.vehicles.Kata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application simulates driving a Rover Kata over Mars.
 */
@SpringBootApplication
public class MarsRoverKataApplication implements ApplicationRunner {
	private static final Logger logger = LoggerFactory.getLogger(MarsRoverKataApplication.class);

	private final ArgumentParser argumentParser;
	private final Kata kata;

	public MarsRoverKataApplication(
			ArgumentParser argumentParser, Kata kata) {
		this.argumentParser = argumentParser;
		this.kata = kata;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarsRoverKataApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {

		// parse the incoming arguments to verify if we can proceed
		var parsedArguments = argumentParser.parse(args);

		// we can proceed with driving Kata, if the arguments are present
		if (parsedArguments.isPresent()) {
			var newLocation = kata.drive(
					parsedArguments.get().getInstructions(),
					parsedArguments.get().getLocation());

			logger.info("New location is: " + newLocation);
		}
	}

}
