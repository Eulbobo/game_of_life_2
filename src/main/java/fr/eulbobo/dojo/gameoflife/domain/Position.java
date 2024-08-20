package fr.eulbobo.dojo.gameoflife.domain;

import java.util.Set;

public record Position(int x, int y) implements Comparable<Position> {

    public Set<Position> neighbours() {
        return Set.of(
                new Position(x - 1, y - 1),
                new Position(x, y - 1),
                new Position(x + 1, y - 1),

                new Position(x - 1, y + 1),
                new Position(x, y + 1),
                new Position(x + 1, y + 1),

                new Position(x - 1, y),
                new Position(x + 1, y)
        );
    }

    @Override
    public int compareTo(Position other) {
        if (y == other.y) {
            return Integer.compare(x, other.x);
        }
        return Integer.compare(y, other.y);
    }
}
