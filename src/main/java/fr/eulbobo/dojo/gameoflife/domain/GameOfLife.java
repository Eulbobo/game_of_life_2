package fr.eulbobo.dojo.gameoflife.domain;

import fr.eulbobo.dojo.gameoflife.domain.display.CellDisplay;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellData;
import fr.eulbobo.dojo.gameoflife.domain.generator.CellGenerator;


public class GameOfLife {

    Position position;
    private GameOfLife(Position position) {
       this.position = position;
    }

    public void displayTo(CellDisplay displayer) {
        displayer.alive(position);
    }

    public static GameOfLife from(CellGenerator cellGenerator) {
        Position position = cellGenerator.allCells().stream().findFirst().map(CellData::getPosition).get();
        return new GameOfLife(position);
    }
}
