package ru.saveldu;

import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivore;
import ru.saveldu.Entities.Predator;
import ru.saveldu.Entities.Predators.Wolf;

public class StatClass {

    private static int countEntity = 0;

    public static void printMap(Cell[][] cells) {
        System.out.println("В каждой ячейке слева - количество травоядных, справа - хищников");
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                System.out.print("| " + cell.getHerbivoreCount() + "|" + cell.getPredatorCount() + " |");
            }
            System.out.println();
        }
        System.out.println("Волков на карте " + Wolf.getCount() + " животных на карте: " + Animal.getCount());


    }
    private static void getPredators(Cell cell) {

        for (Predator predator : cell.getPredators()) {
            System.out.println(predator.toString());

        }

    }
    private static void getHerbivores(Cell cell) {

        for (Herbivore herbivore : cell.getHerbivores()) {
            System.out.println(herbivore.toString());
        }

    }
    public static void getStat(Island island) {
        countEntity = 0;

        for (Cell[] cell : island.getCells()) {
            for (Cell cell1 : cell) {
                System.out.println("Ячейка " + cell1.getX() + " " + cell1.getY() + ": ");
                getPredators(cell1);
                getHerbivores(cell1);
                countEntity = countEntity +  cell1.getHerbivores().size() + cell1.getPredators().size();
            }

        }


        System.out.println("На острове сейчас " + countEntity + " животных");
        System.out.println();
        System.out.println("----------------------------------------------");
        System.out.println();

    }

}
