package ru.saveldu;

import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Grass;
import ru.saveldu.Entities.Herbivores.GuineaPig;
import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Entities.Predators.Wolf;
import ru.saveldu.Utils.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ru.saveldu.Utils.AnimalEatProbability.getPair;

public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Hello Island!");
        LoadClass loadClass = new LoadClass();
        Island island = Island.getInstance(10, 5);

        LoadClass.InitializeClass.initialize();

        StatClass.printMap(island.getCells());
        StepClass stepClass = new     StepClass();
        stepClass.run();
        System.out.println("debug");


    }
}