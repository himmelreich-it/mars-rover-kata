package com.equalexperts.kata;

import com.equalexperts.kata.location.Location;

/**
 * This class represents the arguments coming from the command line.
 */
public class ParsedArguments {
  private Location location;
  private String instructions;

  public ParsedArguments(Location location, String instructions) {
    this.location = location;
    this.instructions = instructions;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getInstructions() {
    return instructions;
  }

  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }
}
