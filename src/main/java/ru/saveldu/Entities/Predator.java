package ru.saveldu.Entities;

import ru.saveldu.Cell;

import java.util.Random;

public abstract class Predator extends Animal {

    public Predator(Cell cell) {
        super(cell);
        cell.addPredator(this);
    }

    @Override
    public void eat() {
        Random random = new Random();

        if (!cell.getHerbivores().isEmpty() && random.nextInt(100) > 60) {
            Herbivore victim = cell.getHerbivores().remove(0);

        } else {

            this.die();
        }

    }

    @Override
    public void die() {
        this.getCell().getPredators().remove(this);
        Animal.setCount(Animal.getCount() - 1);
    }
}
