package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivore;
import ru.saveldu.Entities.Herbivores.*;
import ru.saveldu.Entities.Predator;
import ru.saveldu.Entities.Predators.*;
import ru.saveldu.Island;

public class StatClass {

    private static int countEntity = 0;


    public static void printMap(Cell[][] cells) {
        System.out.println("В каждой ячейке слева - количество травоядных, справа - хищников");

        for (int x = 0; x < cells[0].length; x++) { // Перебираем по столбцам (X)
            for (int y = 0; y < cells.length; y++) { // Перебираем по строкам (Y)
                System.out.print("| " + cells[y][x].getHerbivoreCount() + "|" + cells[y][x].getPredatorCount() + " |");
            }
            System.out.println();
        }
        System.out.println(Wolf.getImage() + Wolf.getCount() +
                Snake.getImage() + Snake.getCount() +
                Fox.getImage() + Fox.getCount() +
                Eagle.getImage() + Eagle.getCount() +
                Bear.getImage() + Bear.getCount() +
                Sheep.getImage() + Sheep.getCount() +
                Rabbit.getImage() + Rabbit.getCount() +
                Kangaroo.getImage() + Kangaroo.getCount() +
                Horse.getImage() + Horse.getCount() +
                Hamster.getImage() + Hamster.getCount() +
                GuineaPig.getImage() + GuineaPig.getCount() +
                Goat.getImage() + Goat.getCount() +
                Duck.getImage() + Duck.getCount() +
                Deer.getImage() + Deer.getCount() +
                Cow.getImage() + Cow.getCount() +
                Caterpillar.getImage() + Caterpillar.getCount() +
                Buffalo.getImage() + Buffalo.getCount() +
                Boar.getImage() + Boar.getCount() + "\n" +

                "Животных на карте: " + Animal.getCount()
        +"\nТакт: " + StepClass.getTickCount() + "\n-------------------------------------------------------\n" );
    }

}
