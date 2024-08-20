package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.mockito.ArgumentMatchers.eq;

public class GameOfLifeTest {

    @Test
    void should_output_cells_to_displayer() {
        CellDisplay displayer = Mockito.mock(CellDisplay.class);
        GameOfLife gameOfLife = GameOfLife.from(singleAliveCellGenerator(2, 2));

        gameOfLife.displayTo(displayer);

        Mockito.verify(displayer).alive(eq(new Position(2, 2)));
    }

    private CellGenerator singleAliveCellGenerator(int x, int y) {
        return () -> Set.of(new CellData() {
            @Override
            public Position getPosition() {
                return new Position(x, y);
            }

            @Override
            public boolean isAlive() {
                return true;
            }
        });
    }
}
