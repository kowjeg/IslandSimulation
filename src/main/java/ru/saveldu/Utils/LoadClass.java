package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivores.*;
import ru.saveldu.Entities.Predators.*;

import java.nio.Buffer;
import java.util.*;

import static ru.saveldu.Utils.AnimalEatProbability.getPair;

public class LoadClass {
    //Мэппинг вероятности поедания
    private static final AnimalEatProbability probabilityTable = new AnimalEatProbability();

    private static final Map<Class<? extends Animal>,Integer> healthMaxMap = new HashMap<>();
//    private static Set<EatPair<Class<? extends Animal>, Class<? extends Animal>>> pairs = getPair(Wolf.class, Rabbit.class);
    private static Map<String,String> stringPair = new HashMap<>();
    private Map<Class<? extends Animal>, Integer> animalPopMap = new HashMap<>();
    private static final Map<Class<? extends Animal>,Integer> stepsMap = new HashMap<>();
    //Набор возможных путей поедания, строящийся на основе заполненной таблицы вероятности поедания. При поедании сначала будет поиск в ячейке такой комбинации
    //по голодному животному, если находит - то идем во вторую таблицу вероятности, и пытаемся покушать
    private static Map<String, List<String>> mapPairs;

    public static Map<String, List<String>> getMapPairs() {
        return mapPairs;
    }

    public static Map<Class<? extends Animal>, Integer> getStepsMap() {
        return stepsMap;
    }
    //    public static Map<String, String> getStringPair() {
//        return stringPair;
//    }

    public AnimalEatProbability getProbabilityTable() {
        return probabilityTable;
    }


    //мапа с изначальным здоровьем каждого объекта класса


    public static Map<Class<? extends Animal>, Integer> getHealthMaxMap() {
        return healthMaxMap;
    }

    //загрузка параметров
    {
        //максимальное количество животных в клетке
        animalPopMap.put(Wolf.class,15);
        animalPopMap.put(Rabbit.class,15);
        animalPopMap.put(Fox.class,10);
        animalPopMap.put(Bear.class,2);
        animalPopMap.put(Eagle.class,10);
        animalPopMap.put(GuineaPig.class,30);
        animalPopMap.put(Horse.class,10);
        animalPopMap.put(Deer.class,10);
        animalPopMap.put(Hamster.class,40);
        animalPopMap.put(Goat.class,30);
        animalPopMap.put(Sheep.class,30);
        animalPopMap.put(Boar.class,8);
        animalPopMap.put(Buffalo.class,5);
        animalPopMap.put(Duck.class,50);
        animalPopMap.put(Caterpillar.class,200);
        animalPopMap.put(Kangaroo.class,7);


        //вероятности поедания
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


        //здоровье по умолчанию/максимальное у животных
        healthMaxMap.put(Wolf.class,5);
        healthMaxMap.put(Rabbit.class,3);
        healthMaxMap.put(Fox.class,4);
        healthMaxMap.put(Bear.class,10);
        healthMaxMap.put(Eagle.class,6);
        healthMaxMap.put(GuineaPig.class,10);
        healthMaxMap.put(Horse.class,5);
        healthMaxMap.put(Deer.class,5);
        healthMaxMap.put(Hamster.class,2);
        healthMaxMap.put(Goat.class,5);
        healthMaxMap.put(Sheep.class,4);
        healthMaxMap.put(Boar.class,6);
        healthMaxMap.put(Buffalo.class,9);
        healthMaxMap.put(Duck.class,3);
        healthMaxMap.put(Caterpillar.class,1);
        healthMaxMap.put(Kangaroo.class,4);

        //максимальное количество шагов за такт для животных
        stepsMap.put(Wolf.class,3);
        stepsMap.put(Rabbit.class,2);
        stepsMap.put(Fox.class,2);
        stepsMap.put(Bear.class,2);
        stepsMap.put(Eagle.class,3);
        stepsMap.put(GuineaPig.class,1);
        stepsMap.put(Horse.class,4);
        stepsMap.put(Deer.class,4);
        stepsMap.put(Hamster.class,1);
        stepsMap.put(Goat.class,3);
        stepsMap.put(Sheep.class,3);
        stepsMap.put(Boar.class,2);
        stepsMap.put(Buffalo.class,3);
        stepsMap.put(Duck.class,4);
        stepsMap.put(Caterpillar.class,0);
        stepsMap.put(Kangaroo.class,3);

        AnimalEatProbability aep = new AnimalEatProbability();

        mapPairs  = AnimalEatProbability.getPairs();





    }
}
