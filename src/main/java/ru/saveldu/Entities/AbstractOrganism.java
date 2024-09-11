package ru.saveldu.Entities;


import ru.saveldu.Cell;

public abstract class AbstractOrganism {


    Cell cell;
    protected boolean isAlive;
    public AbstractOrganism(Cell cell) {
        this.cell = cell;
        isAlive = true;

    }
    public Cell getCell() {

        return cell;
    }
}
