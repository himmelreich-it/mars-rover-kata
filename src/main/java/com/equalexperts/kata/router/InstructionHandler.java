package com.equalexperts.kata.router;

import com.equalexperts.kata.location.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * This class is able to handle a instruction to a vehicle. Currently we only support
 * moving the vehicle.
 */
@Service
public class InstructionHandler {
  private static final Logger logger = LoggerFactory.getLogger(InstructionHandler.class);

  private final VehicleMover vehicleMover;

  public InstructionHandler(VehicleMover vehicleMover) {
    this.vehicleMover = vehicleMover;
  }

  /**
   * Handle the specific instruction, either moving the car or turning.
   * @param instruction Instruction to handle
   * @param currentLocation Current location of the vehicle
   * @return Returns the new location after the instructions is processed
   */
  public Location handleInstruction(char instruction, Location currentLocation) {

    // F -> Move forward on current heading
    // B -> Move backwards on current heading
    // L -> Rotate left by 90 degrees
    // R -> Rotate right by 90 degrees
    switch (instruction) {
      case 'F':
      case 'B':
        return vehicleMover.move(instruction, currentLocation);
      case 'L':
      case 'R':
        return new Location(
            currentLocation.getX(),
            currentLocation.getY(),
            vehicleMover.turn(instruction, currentLocation.getDirection()));
      default:
        logger.warn("Invalid instruction found, ignored: " + instruction);
        return currentLocation;
    }
  }








}
