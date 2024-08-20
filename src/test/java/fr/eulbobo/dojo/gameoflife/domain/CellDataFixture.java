package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;

public class CellDataFixture implements CellData {

    private final boolean alive;
    private final int x;
    private final int y;

    public CellDataFixture(boolean alive, int x, int y) {
        this.alive = alive;
        this.x = x;
        this.y = y;
    }

    public static CellDataFixture alive(int x, int y) {
        return new CellDataFixture(true, x, y);
    }

    public static CellDataFixture dead(int x, int y) {
        return new CellDataFixture(false, x, y);
    }

    @Override
    public Position getPosition() {
        return new Position(x, y);
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

}
