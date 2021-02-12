package com.equalexperts.kata.router;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


import com.equalexperts.kata.location.Direction;
import com.equalexperts.kata.location.Location;
import org.junit.jupiter.api.Test;

public class InstructionHandlerTests {

  @Test
  void handleInstructionTest() {
    var vehicleMover = mock(VehicleMover.class);

    var startLocation = new Location(1, 2, Direction.NORTH);
    var endLocation1 = new Location(1, 3, Direction.NORTH);
    var endLocation2 = new Location(1, 1, Direction.NORTH);

    doReturn(endLocation1).when(vehicleMover).move('F', startLocation);
    doReturn(endLocation2).when(vehicleMover).move('B', startLocation);
    doReturn(Direction.WEST).when(vehicleMover).turn('L', Direction.NORTH);
    doReturn(Direction.EAST).when(vehicleMover).turn('R', Direction.NORTH);

    var handler = new InstructionHandler(vehicleMover);
    var newLoc = handler.handleInstruction('F', startLocation);
    assertThat(newLoc.getX()).isEqualTo(1);
    assertThat(newLoc.getY()).isEqualTo(3);
    assertThat(newLoc.getDirection()).isEqualTo(Direction.NORTH);

    newLoc = handler.handleInstruction('B', startLocation);
    assertThat(newLoc.getX()).isEqualTo(1);
    assertThat(newLoc.getY()).isEqualTo(1);
    assertThat(newLoc.getDirection()).isEqualTo(Direction.NORTH);

    newLoc = handler.handleInstruction('L', startLocation);
    assertThat(newLoc.getX()).isEqualTo(1);
    assertThat(newLoc.getY()).isEqualTo(2);
    assertThat(newLoc.getDirection()).isEqualTo(Direction.WEST);

    newLoc = handler.handleInstruction('R', startLocation);
    assertThat(newLoc.getX()).isEqualTo(1);
    assertThat(newLoc.getY()).isEqualTo(2);
    assertThat(newLoc.getDirection()).isEqualTo(Direction.EAST);
  }





}
