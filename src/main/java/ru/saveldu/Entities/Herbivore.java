package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Island;

public abstract class Herbivore extends Animal {

    public Herbivore(Cell cell) {
        super(cell);
        cell.addHerbivore(this);
    }

    @Override
    public void eat() {
        // Deer eats plants
    }

    @Override
    public void die() {
        this.getCell().getHerbivores().remove(this);
    }
}
