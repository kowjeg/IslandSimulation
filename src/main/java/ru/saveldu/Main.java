package ru.saveldu;

import ru.saveldu.Entities.Rabbit;
import ru.saveldu.Entities.Wolf;
import ru.saveldu.Utils.AnimalFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Island island = new Island(2,3);

        Cell[][] cells = island.getCells();
        new Rabbit(cells[1][1]);
        new Wolf(cells[1][0]);

        StatClass.getStat(island);
        new Wolf(cells[1][0]);
        new Rabbit(cells[1][1]);
        new Rabbit(cells[1][1]);
        Wolf wolf = new Wolf(cells[1][1]);
//        wolf.eat();

        StatClass.getStat(island);
        wolf.eat();
        StatClass.getStat(island);
        StatClass.printMap(island.getCells());

    }
}