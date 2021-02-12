package com.equalexperts.kata.router;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.equalexperts.kata.location.Direction;
import com.equalexperts.kata.location.Location;
import org.junit.jupiter.api.Test;

public class VehicleMoverTests {

  @Test
  void moveTest() {
    var axisCalculator = mock(AxisCalculator.class);

    doReturn(1).when(axisCalculator).getAxisModifier('F', Direction.NORTH);
    doReturn(-1).when(axisCalculator).getAxisModifier('B', Direction.SOUTH);
    doReturn(1).when(axisCalculator).getAxisModifier('F', Direction.EAST);
    doReturn(-1).when(axisCalculator).getAxisModifier('B', Direction.WEST);
    when(axisCalculator.getAxisModifier('F', Direction.NORTH))
        .thenReturn(1)
        .thenReturn(-1);

    var mover = new VehicleMover(axisCalculator);

    var newLocation = mover
        .move('F', new Location(2, 2, Direction.NORTH));
    assertThat(newLocation.getX()).isEqualTo(2);
    assertThat(newLocation.getY()).isEqualTo(3);

    newLocation = mover
        .move('B', new Location(2, 2, Direction.SOUTH));
    assertThat(newLocation.getX()).isEqualTo(2);
    assertThat(newLocation.getY()).isEqualTo(1);

    newLocation = mover
        .move('F', new Location(2, 2, Direction.EAST));
    assertThat(newLocation.getX()).isEqualTo(3);
    assertThat(newLocation.getY()).isEqualTo(2);

    newLocation = mover
        .move('B', new Location(2, 2, Direction.WEST));
    assertThat(newLocation.getX()).isEqualTo(1);
    assertThat(newLocation.getY()).isEqualTo(2);
  }

  @Test
  void moveWithCorrectionTest() {
    var axisCalculator = mock(AxisCalculator.class);

    doReturn(1).when(axisCalculator).getAxisModifier('F', Direction.NORTH);
    doReturn(-1).when(axisCalculator).getAxisModifier('F', Direction.SOUTH);
    doReturn(1).when(axisCalculator).getAxisModifier('F', Direction.EAST);
    doReturn(-1).when(axisCalculator).getAxisModifier('F', Direction.WEST);

    var mover = new VehicleMover(axisCalculator);

    var newLocation = mover
        .move('F', new Location(10, 10, Direction.NORTH));
    assertThat(newLocation.getX()).isEqualTo(10);
    assertThat(newLocation.getY()).isEqualTo(-10);

    newLocation = mover
        .move('F', new Location(-10, -10, Direction.SOUTH));
    assertThat(newLocation.getX()).isEqualTo(-10);
    assertThat(newLocation.getY()).isEqualTo(10);

    newLocation = mover
        .move('F', new Location(10, 10, Direction.EAST));
    assertThat(newLocation.getX()).isEqualTo(-10);
    assertThat(newLocation.getY()).isEqualTo(10);

    newLocation = mover
        .move('F', new Location(-10, -10, Direction.WEST));
    assertThat(newLocation.getX()).isEqualTo(10);
    assertThat(newLocation.getY()).isEqualTo(-10);
  }

  @Test
  void turnTests() {
    var mover = new VehicleMover(mock(AxisCalculator.class));

    assertThat(mover.turn('R', Direction.NORTH)).isEqualTo(Direction.EAST);
    assertThat(mover.turn('R', Direction.EAST)).isEqualTo(Direction.SOUTH);
    assertThat(mover.turn('R', Direction.SOUTH)).isEqualTo(Direction.WEST);
    assertThat(mover.turn('R', Direction.WEST)).isEqualTo(Direction.NORTH);

    assertThat(mover.turn('L', Direction.NORTH)).isEqualTo(Direction.WEST);
    assertThat(mover.turn('L', Direction.WEST)).isEqualTo(Direction.SOUTH);
    assertThat(mover.turn('L', Direction.SOUTH)).isEqualTo(Direction.EAST);
    assertThat(mover.turn('L', Direction.EAST)).isEqualTo(Direction.NORTH);
  }

}
