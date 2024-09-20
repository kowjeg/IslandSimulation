package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Island;
import ru.saveldu.Utils.LoadClass;

public abstract class Herbivore extends Animal {

    public Herbivore(Cell cell) {
        super(cell);
        cell.addAnimal(this);

    }

    @Override
    public void eat() {

//        cell.getPlants().remove(0);

//        System.out.println("покушал");
        health--;

        // Deer eats plants
    }
}
