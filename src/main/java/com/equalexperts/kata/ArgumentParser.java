package com.equalexperts.kata;

import com.equalexperts.kata.location.Direction;
import com.equalexperts.kata.location.Location;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Service;

/**
 * This service parses the arguments from the command line for the Kata driving instructions.
 */
@Service
public class ArgumentParser {

  private static final Logger logger = LoggerFactory.getLogger(ArgumentParser.class);

  /**
   * This methods converts the arguments given to the Kata location and instructions.
   * @param args Spring Boot Application Arguments
   * @return Optional of ParsedArguments when proper arguments are passed, otherwise Optional.empty
   */
  public Optional<ParsedArguments> parse(ApplicationArguments args) {
    int x, y;
    Direction direction;

    var arguments = args.getNonOptionArgs();

    // we cannot continue if we do not have all required arguments
    if (arguments.size() != 4) {
      logger.error("Missing correct arguments to set the initial Rover location and " +
          "moving instructions, eg.: \"2 3 NORTH LLFBRFFL\"");
      return Optional.empty();
    }

    // get the start x position
    try {
      x = Integer.parseInt(arguments.get(0));
    } catch (Exception e) {
      logger.error("x location not an integer, eg.: \"2\"");
      return Optional.empty();
    }

    // get the start y position
    try {
      y = Integer.parseInt(arguments.get(1));
    } catch (Exception e) {
      logger.error("y location not an integer, eg.: \"2\"");
      return Optional.empty();
    }

    // get the start directions
    try {
      direction = Direction.valueOf(arguments.get(2));
    } catch (Exception e) {
      logger.error("Direction not correct, possible values.: NORTH, EAST, SOUTH, WEST");
      return Optional.empty();
    }

    var initialLocation = new Location(x, y, direction);
    // get the driving instructions
    var instructions = arguments.get(3);

    return Optional.of(new ParsedArguments(initialLocation, instructions));
  }


}
