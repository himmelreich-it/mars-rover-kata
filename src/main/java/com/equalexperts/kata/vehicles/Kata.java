package com.equalexperts.kata.vehicles;

import com.equalexperts.kata.location.Location;
import com.equalexperts.kata.router.InstructionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class represents the Kata Vehicle and is able to handle any actions required.
 * The MVP can only drive...
 */
@Service
public class Kata {

  private static final Logger logger = LoggerFactory.getLogger(Kata.class);

  private InstructionHandler instructionHandler;

  /**
   * Kata Constructor with InstructionHandler
   * @param instructionHandler
   */
  public Kata(InstructionHandler instructionHandler) {
    this.instructionHandler = instructionHandler;
  }

  /**
   * Calling this action allows the Kata to Drive, given a start location an instructions.
   * @param instructions Driving instructions as a String
   * @param start start location of the Kata
   * @return Returns the new Location of the Kata
   */
  public Location drive(String instructions, Location start) {

    var currentLocation = start;

    for (int i = 0; i < instructions.length(); i++) {
      currentLocation = instructionHandler.handleInstruction(instructions.charAt(i), currentLocation);
    }

    return currentLocation;
  }

}
