package ru.saveldu.Entities;

import ru.saveldu.Cell;

import java.util.Objects;

public abstract class Animal extends AbstractOrganism {
    String imageText;
    private int stepSize;

    public Animal(Cell cell) {
        super(cell);

    }

    public abstract void move(Cell cell);
    public abstract void eat();
    public void reproduce() {

    };
    public abstract void die();


    public void chooseMove() {
        System.out.println("Двигаюсь в ячейку :");
    }

    public boolean isAlive() {
        return isAlive;
    }


}
