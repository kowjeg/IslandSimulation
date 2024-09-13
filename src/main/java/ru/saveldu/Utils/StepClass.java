package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Island;

import java.util.ArrayList;
import java.util.List;

public class StepClass implements Runnable {

    @Override
    public void run() {

        while (true) {
            List<Animal> allAnimals = ListUtils.getAllAnimals();
            for (Animal animal : allAnimals) {
                animal.move();

            }

        }



    }
}
