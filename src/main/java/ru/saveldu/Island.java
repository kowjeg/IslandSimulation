package ru.saveldu;

public class Island {

    private Cell[][] cells;
    private int width;
    private int height;
    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        initializeCells();
    }
    public Cell[][] getCells() {
        return cells;
    }
    public void setCells(Cell[][] cells) {}

    public void initializeCells() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

}
