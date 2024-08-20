package fr.eulbobo.dojo.gameoflife.domain.generator;

import fr.eulbobo.dojo.gameoflife.domain.Position;

public interface CellData {
    Position getPosition();

    boolean isAlive();
}
