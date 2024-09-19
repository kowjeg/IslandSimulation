package ru.saveldu.Utils;

import ru.saveldu.Entities.Animal;

import java.util.*;


//отдельный класс для логики поедания, загрузка в LoadClass
public class AnimalEatProbability {
    private static Map<EatPair<Class<? extends Animal>, Class<? extends Animal>>, Double> probabilityMap = new HashMap<>();

    public static void setProbabilityMap(Map<EatPair<Class<? extends Animal>, Class<? extends Animal>>, Double> probabilityMap) {
        AnimalEatProbability.probabilityMap = probabilityMap;

    }

    public void addProbability(Class<? extends Animal> clazzEater, Class<? extends Animal> clazzVictim, double probability) {
        EatPair<Class<? extends Animal>, Class<? extends Animal>> pair = new EatPair<>(clazzEater, clazzVictim);
        probabilityMap.put(pair, probability);
    }

    public static double getProbability(Class<? extends Animal> clazzEater, Class<? extends Animal> clazzVictim) {
        EatPair<? extends Class<? extends Animal>, ? extends Class<? extends Animal>> pair = new EatPair<>(clazzEater, clazzVictim);
        return probabilityMap.getOrDefault(pair, 0.0); // Если нет значения, возвращается 0.0
    }

    public static Set<EatPair<Class<? extends Animal>, Class<? extends Animal>>> getPair (Class<? extends Animal> clazzEater, Class<? extends Animal> clazzVictim)  {
        return probabilityMap.keySet();
    }


    public static Map<String, List<String>> getPairs() {
        Map<String, List<String>> classPairs = new HashMap<>();

        for (EatPair<Class<? extends Animal>, Class<? extends Animal>> key : probabilityMap.keySet()) {
            // Извлекаем имена классов
            String predatorName = key.getFirst().getSimpleName();
            String herbivoreName = key.getSecond().getSimpleName();

            // Добавляем травоядное к хищнику, если он уже есть в мапе
            classPairs.putIfAbsent(predatorName, new ArrayList<>());
            classPairs.get(predatorName).add(herbivoreName);
        }

        return classPairs;
    }
}


