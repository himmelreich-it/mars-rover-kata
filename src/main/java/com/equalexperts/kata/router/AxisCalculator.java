package com.equalexperts.kata.router;

import com.equalexperts.kata.location.Direction;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * This class helps calculating operations required on the axis of the surface of Mars.
 */
@Service
public class AxisCalculator {

  private Map<Direction, Integer> axisModifiers = Map.of(
      Direction.NORTH, 1,
      Direction.EAST, 1,
      Direction.SOUTH, -1,
      Direction.WEST, -1
  );

  /**
   * Get the axis modifier given an action (drive Forward F or Back B) and the current
   * direction the vehicle is facing.
   * @param action F for Forward, B for Backwards, any other action would not be processed.
   * @param direction The current direction of the vehicle.
   * @return 1 or -1 depending on action and direction, 0 if the action is not supported, the
   * vehicle will not move.
   */
  public int getAxisModifier(char action, Direction direction) {

    // we need to know which way to go, if going forward
    var axisModifier = axisModifiers.getOrDefault(direction, 0);

    // if we go back, reverse
    if (action == 'B') {
      axisModifier = -axisModifier;
    }

    return axisModifier;
  }
}
