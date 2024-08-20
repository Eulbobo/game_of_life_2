package fr.eulbobo.dojo.gameoflife.primary.generator;

import fr.eulbobo.dojo.gameoflife.domain.Position;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class StringCellGenerator implements CellGenerator {

    List<CellData> cells = new ArrayList<>();

    public StringCellGenerator(String input, char aliveCell) {
        AtomicInteger y = new AtomicInteger(0);
        input.lines().forEach(l -> {
            for (int x = 0; x < l.length(); x++) {
                newCell(x, y.get(), l.charAt(x) == aliveCell);
            }
            y.incrementAndGet();
        });
    }

    private void newCell(int x, int y, boolean alive) {
        cells.add(new InnerCellData(new Position(x, y), alive));
    }

    @Override
    public Collection<CellData> allCells() {
        return cells;
    }

    private record InnerCellData(Position position, boolean alive) implements CellData {

        @Override
        public Position getPosition() {
            return position;
        }

        @Override
        public boolean isAlive() {
            return alive;
        }
    }
}
