package com.equalexperts.kata;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import com.equalexperts.kata.location.Direction;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;

public class ArgumentParserTests {

  @Test
  void parseTest() {
    var parser = new ArgumentParser();

    var invalidNumberOfArgs = List.of("2", "3");
    var invalidX = List.of("L", "3", "NORTH", "FFLB");
    var invalidY = List.of("2", "3.2", "NORTH", "FFLB");
    var invalidDirection = List.of("2", "3", "UP", "FFLB");
    var correctList = List.of("2", "3", "NORTH", "FFLB");

    var args = mock(ApplicationArguments.class);

    when(args.getNonOptionArgs())
        .thenReturn(invalidNumberOfArgs)
        .thenReturn(invalidX)
        .thenReturn(invalidY)
        .thenReturn(invalidDirection)
        .thenReturn(correctList);

    // first four version are not correct
    assertThat(parser.parse(args)).isEmpty();
    assertThat(parser.parse(args)).isEmpty();
    assertThat(parser.parse(args)).isEmpty();
    assertThat(parser.parse(args)).isEmpty();

    // only the fifth is in the correct format
    var optionalParsedArguments = parser.parse(args);
    assertThat(optionalParsedArguments).isPresent();

    var location = optionalParsedArguments.get().getLocation();
    assertThat(location.getX()).isEqualTo(2);
    assertThat(location.getY()).isEqualTo(3);
    assertThat(location.getDirection()).isEqualTo(Direction.NORTH);

    var instructions = optionalParsedArguments.get().getInstructions();
    assertThat(instructions).isEqualTo("FFLB");
    // TODO add instructions


  }
}
