package ru.saveldu;

import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Entities.Predators.Wolf;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        Island island = Island.getInstance(6, 5);

        Cell[][] cells = island.getCells();

        Rabbit rabbit = new Rabbit(cells[1][0]);

        Wolf wolf = new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);
        new Wolf(cells[1][1]);


        StatClass.printMap(cells);
        StatClass.getStat(island);

//        System.out.println(wolf.checkOutOfBounds(4,3));


        while (true) {

            wolf.move();
//            rabbit.move();
//          T
            Thread.sleep(1000);
            StatClass.printMap(cells);

        }


    }
}