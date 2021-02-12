package com.equalexperts.kata.router;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import com.equalexperts.kata.location.Direction;
import com.equalexperts.kata.location.Location;
import org.junit.jupiter.api.Test;

public class AxisCalculatorTests {
  @Test
  void getAxisModifierTests() {
    var mover = new AxisCalculator();

    // check modifier moving forward
    assertThat(mover.getAxisModifier('F', Direction.NORTH)).isEqualTo(1);
    assertThat(mover.getAxisModifier('F', Direction.EAST)).isEqualTo(1);
    assertThat(mover.getAxisModifier('F', Direction.SOUTH)).isEqualTo(-1);
    assertThat(mover.getAxisModifier('F', Direction.WEST)).isEqualTo(-1);

    // the reverse for going back
    assertThat(mover.getAxisModifier('B', Direction.NORTH)).isEqualTo(-1);
    assertThat(mover.getAxisModifier('B', Direction.EAST)).isEqualTo(-1);
    assertThat(mover.getAxisModifier('B', Direction.SOUTH)).isEqualTo(1);
    assertThat(mover.getAxisModifier('B', Direction.WEST)).isEqualTo(1);
  }


}
