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
        } else
        if (animalClass == Wolf.class) {
            return new Wolf( cell);
        }else
        if (animalClass == Boar.class) {
            return new Boar( cell);
        }else
        if (animalClass == Buffalo.class) {
            return new Buffalo(cell);
        }else
        if (animalClass == Caterpillar.class) {
            return new Caterpillar( cell);
        }else
        if (animalClass == Cow.class) {
            return new Cow( cell);
        }else
        if (animalClass == Deer.class) {
            return new Deer( cell);
        }else
        if (animalClass == Duck.class) {
            return new Duck( cell);
        }else
        if (animalClass == Goat.class) {
            return new Goat( cell);
        }else
        if (animalClass == GuineaPig.class) {
            return new GuineaPig( cell);
        }else
        if (animalClass == Hamster.class) {
            return new Hamster( cell);
        }else
        if (animalClass == Horse.class) {
            return new Horse( cell);
        }else
        if (animalClass == Kangaroo.class) {
            return new Kangaroo( cell);
        }else
        if (animalClass == Sheep.class) {
            return new Sheep( cell);
        }else
        if (animalClass == Bear.class) {
            return new Bear( cell);
        }else
        if (animalClass == Eagle.class) {
            return new Eagle( cell);
        }else
        if (animalClass == Fox.class) {
            return new Fox( cell);
        }else
        if (animalClass == Snake.class) {
            return new Snake( cell);
        }   else {
            System.err.println(animalClass + " не смог размножиться");
            return null;
        }
    }

}
