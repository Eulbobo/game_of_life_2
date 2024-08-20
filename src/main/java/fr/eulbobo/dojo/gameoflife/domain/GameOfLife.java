package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


public class GameOfLife {

    Collection<Position> cells;

    private GameOfLife(Collection<Position> cells) {
        this.cells = cells;
    }

    public static GameOfLife from(CellGenerator cellGenerator) {
        Set<Position> positions = cellGenerator.allCells().stream().map(CellData::getPosition).collect(Collectors.toSet());
        return new GameOfLife(positions);
    }

    public void displayTo(CellDisplay displayer) {
        cells.forEach(displayer::alive);
    }
}
