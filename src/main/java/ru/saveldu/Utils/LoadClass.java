package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivores.*;
import ru.saveldu.Entities.Predators.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

import static ru.saveldu.Utils.AnimalEatProbability.getPair;

public class LoadClass {
    //Мэппинг вероятности поедания
    private static final AnimalEatProbability probabilityTable = new AnimalEatProbability();

    private static Map<Class<? extends Animal>,Integer> healthMaxMap = new HashMap<>();
//    private static Set<EatPair<Class<? extends Animal>, Class<? extends Animal>>> pairs = getPair(Wolf.class, Rabbit.class);
    private static Map<String,String> stringPair = new HashMap<>();
    private Map<Class<? extends Animal>, Integer> animalMaxPopulation = new HashMap<>();
    static Map<Class<? extends Animal>,Integer> stepsMap = new HashMap<>();
    //Набор возможных путей поедания, строящийся на основе заполненной таблицы вероятности поедания. При поедании сначала будет поиск в ячейке такой комбинации
    //по голодному животному, если находит - то идем во вторую таблицу вероятности, и пытаемся покушать
    private static Map<String, List<String>> mapPairs;

    public static Map<String, List<String>> getMapPairs() {
        return mapPairs;
    }

    public static Map<Class<? extends Animal>, Integer> getStepsMap() {
        return stepsMap;
    }

    public AnimalEatProbability getProbabilityTable() {
        return probabilityTable;
    }

    public static Map<Class<? extends Animal>, Integer> getHealthMaxMap() {
        return healthMaxMap;
    }
    //загрузка параметров
    {

        AnimalEatProbability aep = new AnimalEatProbability();

        mapPairs  = AnimalEatProbability.getPairs();

        healthMaxMap = PropertiesLoader.loadPropertyMap("src/main/resources/maxhealth.properties");

        //загрузка из файла стартовой популяции каждого вида животных при инициализации
        animalMaxPopulation = PropertiesLoader.loadPropertyMap("src/main/resources/animalstartpopulation.properties");

        //загрузка из файла максимального количества шагов за такт для животных из настройки
        stepsMap = PropertiesLoader.loadPropertyMap("src/main/resources/stepmap.properties");

        //загрузка из файла меппинга вероятностей поедания
        AnimalEatProbability.setProbabilityMap(PropertiesLoader.loadAnimalEatProbability("src/main/resources/animalEatProbability.properties"));

    }
    public static class PropertiesLoader {
        //меппинг короткого имени класса с полным
        private static final Map<String, String> animalClassMap = new HashMap<>();

        static {
            // Заполняем мапу соответствиями из проперти и полного пути до класса
            animalClassMap.put("rabbit", "ru.saveldu.Entities.Herbivores.Rabbit");
            animalClassMap.put("wolf", "ru.saveldu.Entities.Predators.Wolf");
            animalClassMap.put("bear", "ru.saveldu.Entities.Predators.Bear");
            animalClassMap.put("eagle", "ru.saveldu.Entities.Predators.Eagle");
            animalClassMap.put("fox", "ru.saveldu.Entities.Predators.Fox");
            animalClassMap.put("snake", "ru.saveldu.Entities.Predators.Snake");
            animalClassMap.put("boar", "ru.saveldu.Entities.Herbivores.Boar");
            animalClassMap.put("buffalo", "ru.saveldu.Entities.Herbivores.Buffalo");
            animalClassMap.put("caterpillar", "ru.saveldu.Entities.Herbivores.Caterpillar");
            animalClassMap.put("cow", "ru.saveldu.Entities.Herbivores.Cow");
            animalClassMap.put("deer", "ru.saveldu.Entities.Herbivores.Deer");
            animalClassMap.put("duck", "ru.saveldu.Entities.Herbivores.Duck");
            animalClassMap.put("goat", "ru.saveldu.Entities.Herbivores.Goat");
            animalClassMap.put("guineapig", "ru.saveldu.Entities.Herbivores.GuineaPig");
            animalClassMap.put("hamster", "ru.saveldu.Entities.Herbivores.Hamster");
            animalClassMap.put("horse", "ru.saveldu.Entities.Herbivores.Horse");
            animalClassMap.put("kangaroo", "ru.saveldu.Entities.Herbivores.Kangaroo");
            animalClassMap.put("sheep", "ru.saveldu.Entities.Herbivores.Sheep");

        }
        public static Map<Class<? extends Animal>,Integer> loadPropertyMap(String filePath) {
            Map<Class<? extends Animal>,Integer> loadedMap = new HashMap<>();
            Properties properties = new Properties();
            try (FileInputStream inputStream = new FileInputStream(filePath)) {
                properties.load(inputStream);

                for (String key : properties.stringPropertyNames()) {
                    String value = properties.getProperty(key);
                    try {
                        // Получаем полное имя класса из мапы
                        String fullClassName = animalClassMap.get(key);
                        if (fullClassName != null) {
                            Class<? extends Animal> animalClass = (Class<? extends Animal>) Class.forName(fullClassName);
                            Integer step = Integer.parseInt(value);
                            loadedMap.put(animalClass, step);
                        } else {
                            System.err.println("Неизвестный класс: " + key);
                        }
                    } catch (ClassNotFoundException e) {
                        System.err.println("Класс не найден: " + key);
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат числа для: " + value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return loadedMap;


        }
        public static Map<EatPair<Class<? extends Animal>, Class<? extends Animal>>, Double> loadAnimalEatProbability(String filePath) {
            Map<EatPair<Class<? extends Animal>, Class<? extends Animal>>, Double> eatMapProb = new HashMap<>();

            Properties properties = new Properties();
            try (FileInputStream inputStream = new FileInputStream(filePath)) {
                properties.load(inputStream);

                for (String key : properties.stringPropertyNames()) {
                    String value = properties.getProperty(key);
                    try {
                        // Разделяем ключ на части
                        String[] classes = key.split(",");
                        if (classes.length == 2) {
                            Class<? extends Animal> firstClass = (Class<? extends Animal>) Class.forName(animalClassMap.get(classes[0].trim()));
                            Class<? extends Animal> secondClass = (Class<? extends Animal>) Class.forName(animalClassMap.get(classes[1].trim()));
                            Double count = Double.parseDouble(value);
                            EatPair<Class<? extends Animal>, Class<? extends Animal>> pair = new EatPair<>(firstClass, secondClass);
                            eatMapProb.put(pair, count);
                        } else {
                            System.err.println("Некорректный формат ключа: " + key);
                        }
                    } catch (ClassNotFoundException e) {
                        System.err.println("Класс не найден: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат числа для: " + value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return eatMapProb;
        }

    }

}

