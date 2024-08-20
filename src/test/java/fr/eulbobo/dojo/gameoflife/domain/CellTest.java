package fr.eulbobo.dojo.gameoflife.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CellTest {

    @ParameterizedTest
    @MethodSource("sourceAliveCell")
    void alive_cell_should_live_or_die_given_circumstances(int aliveNeighbours, boolean alive) {
        Cell cell = Cell.aliveCell();
        cell.hasAliveNeighbours(aliveNeighbours);

        Assertions.assertThat(Cell.isAlive(cell)).isEqualTo(alive);
    }

    public static Stream<Arguments> sourceAliveCell() {
        return Stream.of(Arguments.of(0, false),
                Arguments.of(1, false),
                Arguments.of(2, true),
                Arguments.of(3, true),
                Arguments.of(4, false),
                Arguments.of(5, false));
    }

    @ParameterizedTest
    @MethodSource("sourceDeadCell")
    void dead_cell_should_live_or_die_given_circumstances(int aliveNeighbours, boolean alive) {
        Cell cell = Cell.deadCell();
        cell.hasAliveNeighbours(aliveNeighbours);

        Assertions.assertThat(Cell.isAlive(cell)).isEqualTo(alive);
    }

    public static Stream<Arguments> sourceDeadCell() {
        return Stream.of(Arguments.of(0, false),
                Arguments.of(1, false),
                Arguments.of(2, false),
                Arguments.of(3, true),
                Arguments.of(4, false),
                Arguments.of(5, false));
    }
}
