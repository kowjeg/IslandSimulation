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

        while (Animal.getCount()>100) {
            List<Animal> allAnimals = ListUtils.getAllAnimals();
            for (Animal animal : allAnimals) {
                animal.move();
            }
            for (Animal animal: allAnimals) {
                animal.reproduce();
            }
            for (Animal animal : allAnimals) {

                animal.eat();
                animal.dieIfNoHealth();

            }
            try {
                Thread.sleep(LoadClass.PropertiesLoader.getTickRate());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            tickCount++;
            StatClass.printMap(Island.getInstance().getCells());
        }
        Island.getInstance().setGameOver(true);
        System.out.println("Конец симуляции, животных <100");

    }
}
