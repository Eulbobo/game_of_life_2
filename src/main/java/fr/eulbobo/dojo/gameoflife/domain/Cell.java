package fr.eulbobo.dojo.gameoflife.domain;

import static fr.eulbobo.dojo.gameoflife.domain.CellStatus.ALIVE;
import static fr.eulbobo.dojo.gameoflife.domain.CellStatus.DEAD;

public class Cell {
    private CellStatus cellStatus;

    private Cell(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public static Cell aliveCell() {
        return new Cell(ALIVE);
    }

    public static Cell deadCell() {
        return new Cell(DEAD);
    }

    public static boolean isAlive(Cell cell) {
        return cell.cellStatus == CellStatus.ALIVE;
    }

    public void ifAliveOrElse(Runnable aliveAction, Runnable deadAction) {
        if (isAlive(this)) {
            aliveAction.run();
        } else {
            deadAction.run();
        }
    }

    public void hasAliveNeighbours(int aliveNeighbours) {
        if (aliveNeighbours < 2 || aliveNeighbours > 3) {
            cellStatus = CellStatus.DEAD;
        }
        if (aliveNeighbours == 3) {
            cellStatus = CellStatus.ALIVE;
        }
    }
}
