package ru.saveldu.Utils;

import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivores.*;
import ru.saveldu.Entities.Predators.*;

import java.nio.Buffer;
import java.util.*;

import static ru.saveldu.Utils.AnimalEatProbability.getPair;

public class LoadClass {
    //Мэппинг вероятности поедания
    private static AnimalEatProbability probabilityTable = new AnimalEatProbability();
    private static Map<Class<? extends Animal>,Integer> healthMap = new HashMap<>();
    private static Set<EatPair<Class<? extends Animal>, Class<? extends Animal>>> pairs = getPair(Wolf.class, Rabbit.class);
    //Набор возможных путей поедания, строящийся на основе заполненной таблицы вероятности поедания. При поедании сначала будет поиск в ячейке такой комбинации
    private static Map<String,String> stringPair = new HashMap<>();
    //по голодному животному, если находит - то идем во вторую таблицу вероятности, и пытаемся покушать
    private static Map<String, List<String>> mapPairs;

    public static Map<String, List<String>> getMapPairs() {
        return mapPairs;
    }

    public static Map<String, String> getStringPair() {
        return stringPair;
    }

    public AnimalEatProbability getProbabilityTable() {
        return probabilityTable;
    }


    //мапа с изначальным здоровьем каждого объекта класса
    public static Map<Class<? extends Animal>, Integer> getHealthMap() {
        return healthMap;
    }

    //загрузка таблицы вероятности поедания
    {
        //wolf
        probabilityTable.addProbability(Wolf.class, Rabbit.class, 0.60);
        probabilityTable.addProbability(Wolf.class, Horse.class, 0.1);
        probabilityTable.addProbability(Wolf.class, Deer.class, 0.15);
        probabilityTable.addProbability(Wolf.class, Hamster.class, 0.8);
        probabilityTable.addProbability(Wolf.class, GuineaPig.class, 0.01);
        probabilityTable.addProbability(Wolf.class, Goat.class, 0.6);
        probabilityTable.addProbability(Wolf.class, Sheep.class, 0.7);
        probabilityTable.addProbability(Wolf.class, Buffalo.class, 0.1);
        probabilityTable.addProbability(Wolf.class, Boar.class, 0.15);
        probabilityTable.addProbability(Wolf.class, Duck.class, 0.4);
        //snake
        probabilityTable.addProbability(Snake.class, Fox.class, 0.15);
        probabilityTable.addProbability(Snake.class, Rabbit.class, 0.7);
        probabilityTable.addProbability(Snake.class, Hamster.class, 0.8);
        probabilityTable.addProbability(Snake.class, GuineaPig.class, 0.01);
        probabilityTable.addProbability(Snake.class, Duck.class, 0.1);
        //fox
        probabilityTable.addProbability(Fox.class, Rabbit.class, 0.70);
        probabilityTable.addProbability(Fox.class, Hamster.class, 0.9);
        probabilityTable.addProbability(Fox.class, Duck.class, 0.6);
        probabilityTable.addProbability(Fox.class, Caterpillar.class, 0.4);
        //bear
        probabilityTable.addProbability(Bear.class, Snake.class, 0.8);
        probabilityTable.addProbability(Bear.class, Horse.class, 0.4);
        probabilityTable.addProbability(Bear.class, Deer.class, 0.8);
        probabilityTable.addProbability(Bear.class, Rabbit.class, 0.8);
        probabilityTable.addProbability(Bear.class, Hamster.class, 0.9);
        probabilityTable.addProbability(Bear.class, Goat.class, 0.7);
        probabilityTable.addProbability(Bear.class, Sheep.class, 0.7);
        probabilityTable.addProbability(Bear.class, Boar.class, 0.5);
        probabilityTable.addProbability(Bear.class, Buffalo.class, 0.2);
        probabilityTable.addProbability(Bear.class, Duck.class, 0.1);

        healthMap.put(Wolf.class,5);
        healthMap.put(Rabbit.class,3);
//        for (EatPair<Class<? extends Animal>, Class<? extends Animal>> pair : pairs) {
//            System.out.println(pair.getFirst().getSimpleName() + " " + pair.getSecond().getSimpleName());
//            stringPair.put(pair.getFirst().getSimpleName(),pair.getSecond().getSimpleName());
//        }
        mapPairs  = probabilityTable.getPairs();

    }
}
