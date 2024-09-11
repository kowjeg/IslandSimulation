package ru.saveldu.Entities;

import ru.saveldu.Cell;

public abstract class Predator extends Animal {



    public Predator(Cell cell) {
        super(cell);
        cell.addPredator(this);
    }



    @Override
    public void eat() {
        System.out.println("Хищник ест");

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void die() {
        this.getCell().getPredators().remove(this);
    }
}
