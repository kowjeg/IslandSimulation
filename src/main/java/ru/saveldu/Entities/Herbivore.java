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
        synchronized (cell.lock) {
//        cell.getPlants().remove(0);
            if (!cell.getPlants().isEmpty()) {
                Plant toEat = cell.getPlants().get(0);
                toEat.die();
                cell.getPlants().remove(toEat);
                health++;
            } else health--;
        }

        // Deer eats plants
    }
}
