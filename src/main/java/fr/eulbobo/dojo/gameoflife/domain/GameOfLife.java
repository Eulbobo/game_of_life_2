package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;

import java.util.HashMap;
import java.util.Map;

public class GameOfLife {

    Map<Position, Cell> cells;

    private GameOfLife(Map<Position, Cell> cells) {
        this.cells = cells;
    }

    public static GameOfLife from(CellGenerator cellGenerator) {
        Map<Position, Cell> allCells = new HashMap<>();
        cellGenerator.allCells().forEach(cellData -> allCells.put(cellData.getPosition(), cellData.isAlive() ? Cell.aliveCell() : Cell.deadCell()));
        return new GameOfLife(allCells);
    }

    public void displayTo(CellDisplay displayer) {
        cells.forEach((position, cell) -> cell.ifAliveOrElse(() -> displayer.alive(position), () -> displayer.dead(position)));
    }
}
