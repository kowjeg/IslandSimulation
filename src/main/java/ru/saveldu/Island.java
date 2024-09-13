package ru.saveldu;

public class Island {

    private static Island instance;
    private Cell[][] cells;
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Island getInstance(int width, int height) {
        if (instance == null) {
            instance = new Island(width, height);
        }
        return instance;
    }
    public static Island getInstance() {
        return instance;
    }

    private Island(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        initializeCells();
    }
    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCellByCoordinates(int x, int y) {
        return cells[x][y];
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
