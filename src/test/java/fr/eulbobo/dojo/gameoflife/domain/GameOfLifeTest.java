package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class GameOfLifeTest {

    @Test
    void should_output_cells_to_displayer() {
        CellDisplay displayer = mock(CellDisplay.class);
        GameOfLife gameOfLife = GameOfLife.from(singleAliveCellGenerator(2, 2));

        gameOfLife.displayTo(displayer);

        verify(displayer).alive(eq(new Position(2, 2)));
    }

    private CellGenerator singleAliveCellGenerator(int x, int y) {
        return () -> Set.of(CellDataFixture.alive(x, y));
    }

    @Test
    void should_output_more_cells_to_displayer() {
        ArgumentCaptor<Position> captor = ArgumentCaptor.forClass(Position.class);
        CellDisplay displayer = mock(CellDisplay.class);

        GameOfLife gameOfLife = GameOfLife.from(aliveAndDeadCells());
        gameOfLife.displayTo(displayer);

        Mockito.verify(displayer, times(2)).alive(captor.capture());
        Assertions.assertThat(captor.getAllValues())
                .containsAll(Set.of(
                        new Position(1, 1),
                        new Position(1, 0))
                );

        Mockito.verify(displayer, times(2)).dead(captor.capture());
        Assertions.assertThat(captor.getAllValues())
                .containsAll(Set.of(
                        new Position(1, 1),
                        new Position(1, 0))
                );
    }

    private CellGenerator aliveAndDeadCells() {
        return () -> Set.of(
                CellDataFixture.alive(1, 1),
                CellDataFixture.alive(1, 0),
                CellDataFixture.dead(0, 1),
                CellDataFixture.dead(0, 0));
    }

    @Test
    void should_output_alive_and_dead_cells_to_displayer() {
        ArgumentCaptor<Position> captor = ArgumentCaptor.forClass(Position.class);
        CellDisplay displayer = mock(CellDisplay.class);

        GameOfLife gameOfLife = GameOfLife.from(manyAliveCellsGenerator());
        gameOfLife.displayTo(displayer);

        Mockito.verify(displayer, times(4)).alive(captor.capture());

        Assertions.assertThat(captor.getAllValues())
                .containsAll(Set.of(
                        new Position(1, 1),
                        new Position(1, 0),
                        new Position(0, 0),
                        new Position(0, 1))
                );
    }

    private CellGenerator manyAliveCellsGenerator() {
        return () -> Set.of(
                CellDataFixture.alive(1, 1),
                CellDataFixture.alive(1, 0),
                CellDataFixture.alive(0, 1),
                CellDataFixture.alive(0, 0));
    }
}
