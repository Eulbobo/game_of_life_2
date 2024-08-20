package fr.eulbobo.dojo.gameoflife.domain;

class Cell {
    private CellStatus cellStatus;
    public Cell(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public static boolean isAlive(Cell cell) {
        return cell.cellStatus == CellStatus.ALIVE;
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
