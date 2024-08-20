package fr.eulbobo.dojo.gameoflife.domain;

class Cell {
    private CellStatus cellStatus;

    private Cell(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    static Cell aliveCell() {
        return new Cell(CellStatus.ALIVE);
    }

    static Cell deadCell() {
        return new Cell(CellStatus.DEAD);
    }

    static boolean isAlive(Cell cell) {
        return cell.cellStatus == CellStatus.ALIVE;
    }

    void ifAliveOrElse(Runnable aliveAction, Runnable deadAction) {
        if (isAlive(this)) {
            aliveAction.run();
        } else {
            deadAction.run();
        }
    }

    void hasAliveNeighbours(long aliveNeighbours) {
        if (aliveNeighbours < 2 || aliveNeighbours > 3) {
            cellStatus = CellStatus.DEAD;
        }
        if (aliveNeighbours == 3) {
            cellStatus = CellStatus.ALIVE;
        }
    }

    private enum CellStatus {
        ALIVE,
        DEAD
    }
}
