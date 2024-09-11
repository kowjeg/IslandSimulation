package ru.saveldu.Entities;

import ru.saveldu.Cell;

public abstract class Herbivore extends Animal {

    public Herbivore(Cell cell) {
        super(cell);
        cell.addHerbivore(this);
    }

    @Override
    public void move(Cell cell) {
        cell.getHerbivores().add(this);

        this.getCell().getHerbivores().remove(this);
        this.cell = cell;
    }

    @Override
    public void eat() {
        // Deer eats plants
    }

    @Override
    public void reproduce() {
        // Deer reproduction logic
    }

    @Override
    public void die() {
        this.getCell().getHerbivores().remove(this);
    }
}
