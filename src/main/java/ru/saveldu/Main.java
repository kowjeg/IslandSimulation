package ru.saveldu;

import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivores.GuineaPig;
import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Entities.Predators.Wolf;
import ru.saveldu.Utils.AnimalEatProbability;

import ru.saveldu.Utils.EatPair;
import ru.saveldu.Utils.LoadClass;
import ru.saveldu.Utils.StatClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.saveldu.Utils.AnimalEatProbability.getPair;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        LoadClass loadClass = new LoadClass();
        Island island = Island.getInstance(6, 5);

        Cell[][] cells = island.getCells();
        Rabbit rabbit = new Rabbit(cells[1][1]);
        Wolf wolf = new Wolf(cells[1][1]);
        System.out.println("-------------");


        wolf.eat();
        System.out.println("-------");
    }
}