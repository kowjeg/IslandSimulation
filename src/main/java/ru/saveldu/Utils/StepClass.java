package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Island;

import java.util.ArrayList;
import java.util.List;

public class StepClass implements Runnable {

    private static int tickCount = 0;

    public static int getTickCount() {
        return tickCount;
    }

    @Override
    public void run() {


        while (true) {
            List<Animal> allAnimals = ListUtils.getAllAnimals();
            for (Animal animal : allAnimals) {
                animal.move();
            }
            for (Animal animal : allAnimals) {

                animal.eat();

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tickCount++;
            StatClass.printMap(Island.getInstance().getCells());
            System.out.println(tickCount);
        }


    }
}
