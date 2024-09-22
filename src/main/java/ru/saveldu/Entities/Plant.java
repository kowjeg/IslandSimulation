package ru.saveldu.Entities;

import ru.saveldu.Cell;

public abstract class Plant extends AbstractOrganism{

    public Plant(Cell cell) {
        super(cell);
        cell.getPlants().add(this);

    }
    public Plant() {}


    public void die() {

        isAlive = false;

    }

}
