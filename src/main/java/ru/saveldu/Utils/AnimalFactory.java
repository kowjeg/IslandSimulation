package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Entities.Predators.Wolf;

public class AnimalFactory {
    public Animal createAnimal(Cell cell, Class<? extends Animal> animalClass){
        if (animalClass == Rabbit.class) {
            return new Rabbit(cell);
        }
        if (animalClass == Wolf.class) {
            return new Wolf(cell);
        }
        else return null;
    }
}
