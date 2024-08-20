package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class GameOfLifeIterationTest {

    @Test
    void should_go_to_next_iteration() {
        CellDisplay displayer = mock(CellDisplay.class);
        GameOfLife gameOfLife = GameOfLife.from(board("""
                ..0
                .0.
                0..
                """));

        gameOfLife.next();
        gameOfLife.displayTo(displayer);

        verify(displayer).alive(eq(new Position(1, 1)));
        verify(displayer, times(8)).dead(any(Position.class));
    }

    @Test
    void should_flip_the_elements() {
        CellDisplay displayer = mock(CellDisplay.class);
        GameOfLife gameOfLife = GameOfLife.from(board("""
                ...
                000
                ...
                """));

        gameOfLife.next();
        gameOfLife.displayTo(displayer);

        verify(displayer).alive(eq(new Position(1, 0)));
        verify(displayer).alive(eq(new Position(1, 1)));
        verify(displayer).alive(eq(new Position(1, 2)));
        verify(displayer, times(6)).dead(any(Position.class));
    }

    @Test
    void should_go_back_to_begining() {
        CellDisplay displayer = mock(CellDisplay.class);
        GameOfLife gameOfLife = GameOfLife.from(board("""
                ...
                000
                ...
                """));

        gameOfLife.next();
        gameOfLife.next();
        gameOfLife.displayTo(displayer);

        verify(displayer).alive(eq(new Position(0, 1)));
        verify(displayer).alive(eq(new Position(1, 1)));
        verify(displayer).alive(eq(new Position(2, 1)));
        verify(displayer, times(6)).dead(any(Position.class));
    }

    private CellGenerator board(String board) {
        HashSet<CellData> cells = new HashSet<>();
        AtomicInteger y = new AtomicInteger(0);
        board.lines().forEach(l -> {
            for (int x = 0; x < l.length(); x++) {
                if (l.charAt(x) == '0') {
                    cells.add(CellDataFixture.alive(x, y.get()));
                } else {
                    cells.add(CellDataFixture.dead(x, y.get()));
                }
            }
            y.incrementAndGet();
        });

        return () -> cells;
    }
}
