package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Island;

import java.util.Objects;

public abstract class Animal extends AbstractOrganism {
    String imageText;
    private int stepSize;

    public Animal(Cell cell) {
        super(cell);

    }

    public abstract void move(Island island);
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
