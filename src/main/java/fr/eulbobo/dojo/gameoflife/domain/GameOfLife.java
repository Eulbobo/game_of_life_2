package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class GameOfLife {

    private final Map<Position, Cell> cells;
    private final Map<Cell, Set<Cell>> cellNeighbours;

    private GameOfLife(Map<Position, Cell> cells) {
        this.cells = cells;
        this.cellNeighbours = defineNeighbourhood();
    }

    public static GameOfLife from(CellGenerator cellGenerator) {
        Map<Position, Cell> allCells = new HashMap<>();
        cellGenerator.allCells().forEach(cellData -> allCells.put(cellData.getPosition(), cellData.isAlive() ? Cell.aliveCell() : Cell.deadCell()));
        return new GameOfLife(allCells);
    }

    private Map<Cell, Set<Cell>> defineNeighbourhood() {
        Map<Cell, Set<Cell>> neighbourhood = new HashMap<>();
        for (Map.Entry<Position, Cell> entry : cells.entrySet()) {
            entry.getKey()
                    .neighbours()
                    .forEach(
                            pos -> Optional.ofNullable(cells.get(pos))
                                    .ifPresent(
                                            cell -> neighbourhood.computeIfAbsent(cell, k -> new HashSet<>()).add(entry.getValue())
                                    )
                    );
        }
        return neighbourhood;
    }

    public void displayTo(CellDisplay displayer) {
        cells.forEach((position, cell) -> cell.ifAliveOrElse(() -> displayer.alive(position), () -> displayer.dead(position)));
    }

    public void next() {
        Set<Cell> allCellsToVerify = getCellsToVerify();
        Map<Cell, Long> aliveNeighbours = new HashMap<>();
        allCellsToVerify.forEach(cell -> aliveNeighbours.put(cell, cellNeighbours.get(cell).stream().filter(Cell::isAlive).count()));
        aliveNeighbours.forEach(Cell::hasAliveNeighbours);
    }

    private Set<Cell> getCellsToVerify() {
        Set<Cell> aliveCells = cells.values().stream().filter(Cell::isAlive).collect(Collectors.toSet());
        Set<Cell> allCellsToVerify = new HashSet<>(aliveCells);
        aliveCells.stream().map(cellNeighbours::get).forEach(allCellsToVerify::addAll);
        return allCellsToVerify;
    }
}
