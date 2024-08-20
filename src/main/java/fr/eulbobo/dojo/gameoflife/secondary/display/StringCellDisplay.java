package fr.eulbobo.dojo.gameoflife.secondary.display;

import fr.eulbobo.dojo.gameoflife.domain.Position;
import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StringCellDisplay implements CellDisplay {

    private final Map<Position, Boolean> points = new HashMap<>();
    private final char alive;
    private final char dead;

    public StringCellDisplay(char alive, char dead) {
        this.alive = alive;
        this.dead = dead;
    }

    private static int positionComparator(Map.Entry<Position, Boolean> pointCellEntry1, Map.Entry<Position, Boolean> pointCellEntry2) {
        return pointCellEntry1.getKey().compareTo(pointCellEntry2.getKey());
    }

    public void printTo(Consumer<String> printer) {
        StringBuilder sb = new StringBuilder();
        points.entrySet().stream().sorted(StringCellDisplay::positionComparator).forEach((entry) -> {
            if (entry.getKey().x() == 0 && !sb.isEmpty()) {
                sb.append(System.lineSeparator());
            }
            sb.append(entry.getValue() ? alive : dead);

        });
        sb.append(System.lineSeparator());
        printer.accept(sb.toString());
    }

    @Override
    public void alive(Position position) {
        points.put(position, Boolean.TRUE);
    }

    @Override
    public void dead(Position position) {
        points.put(position, Boolean.FALSE);
    }
}
