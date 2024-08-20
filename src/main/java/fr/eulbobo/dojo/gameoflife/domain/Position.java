package fr.eulbobo.dojo.gameoflife.domain;

import java.util.Set;

public record Position(int x, int y) {

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
}
