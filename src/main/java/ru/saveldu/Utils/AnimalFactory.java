package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivores.*;
import ru.saveldu.Entities.Predators.*;

import java.util.List;

public class AnimalFactory {
    public static Animal createAnimal(Cell cell, Class<? extends Animal> animalClass){
        if (animalClass == Rabbit.class) {
            return new Rabbit(cell);
        }
        if (animalClass == Wolf.class) {
            return new Wolf( cell);
        }
        if (animalClass == Boar.class) {
            return new Boar( cell);
        }
        if (animalClass == Buffalo.class) {
            return new Buffalo(cell);
        }
        if (animalClass == Caterpillar.class) {
            return new Caterpillar( cell);
        }
        if (animalClass == Cow.class) {
            return new Cow( cell);
        }
        if (animalClass == Deer.class) {
            return new Deer( cell);
        }
        if (animalClass == Duck.class) {
            return new Duck( cell);
        }
        if (animalClass == Goat.class) {
            return new Goat( cell);
        }
        if (animalClass == GuineaPig.class) {
            return new GuineaPig( cell);
        }
        if (animalClass == Hamster.class) {
            return new Hamster( cell);
        }
        if (animalClass == Horse.class) {
            return new Horse( cell);
        }if (animalClass == Kangaroo.class) {
            return new Kangaroo( cell);
        }
        if (animalClass == Sheep.class) {
            return new Sheep( cell);
        }
        if (animalClass == Bear.class) {
            return new Bear( cell);
        }
        if (animalClass == Eagle.class) {
            return new Eagle( cell);
        }
        if (animalClass == Fox.class) {
            return new Fox( cell);
        }
        if (animalClass == Snake.class) {
            return new Snake( cell);
        }

        else return null;
    }

}
