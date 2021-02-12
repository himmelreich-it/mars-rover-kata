package com.equalexperts.kata.router;

import com.equalexperts.kata.location.Direction;
import com.equalexperts.kata.location.Location;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;

/**
 * This class handles moving the vehicle.
 */
@Service
public class VehicleMover {

  private final AxisCalculator axisCalculator;

  public VehicleMover(AxisCalculator axisCalculator) {
    this.axisCalculator = axisCalculator;
  }

  /**
   * Move the vehicle either forward or backward.
   * @param action F for Forward, B for Backwards, any other action would not be processed.
   * @param location current location of the vehicle
   * @return The new location the vehicle.
   */
  public Location move(char action, Location location) {

    // first we need to know what direction we go
    var axisModifier = axisCalculator.getAxisModifier(action, location.getDirection());

    Supplier<Integer> getter = null;
    Consumer<Integer> setter = null;

    switch (location.getDirection()) {
      case NORTH:
      case SOUTH:
        getter = location::getY;
        setter = location::setY;
        break;
      case EAST:
      case WEST:
        getter = location::getX;
        setter = location::setX;
        break;
    }

    // if we have the correct location set
    if (getter != null && setter != null) {
      var newPosition = getter.get() + axisModifier;

      // of the vehicle moved around mars we need to correct the coordinates
      if (newPosition == 11) {
        newPosition = -10;
      } else if (newPosition == -11) {
        newPosition = 10;
      }

      setter.accept(newPosition);
    }

    return location;
  }

  /**
   * Calculates the new direction given a turn direction and a current direction .
   * @param action Should be 'L' or 'R', any other character keeps the same direction
   * @param startDirection Current direction
   * @return Returns a new location with the new direction
   */
  public Direction turn(char action, Direction startDirection) {
    // R = 1, L = -1, otherwise 0
    var modifier = action == 'R' ? 1 : action == 'L' ? -1 : 0;
    var newDirection = startDirection.getValue() + modifier;
    // direction 5 = 0 (from west to north)
    if (newDirection == 5) {
      newDirection = 1;
    }
    // direction 0 = 4 (from north to west)
    if (newDirection == 0) {
      newDirection = 4;
    }

    return Direction.valueOf(newDirection);
  }
}
