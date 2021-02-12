package com.equalexperts.kata.location;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the Direction a vehicle is facing.
 */
public enum Direction {
  NORTH(1),
  EAST(2),
  SOUTH(3),
  WEST(4);

  private int value;
  private static Map map = new HashMap<Integer, Direction>();

  private Direction(int value) {
    this.value = value;
  }

  static {
    for (Direction direction : Direction.values()) {
      map.put(direction.value, direction);
    }
  }

  public static Direction valueOf(int direction) {
    return (Direction) map.get(direction);
  }

  public int getValue() {
    return value;
  }

}
