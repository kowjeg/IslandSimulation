package ru.saveldu.Utils;


import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Island;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LoadClass {
    //Мэппинг вероятности поедания


    private static Map<Class<? extends Animal>, Integer> healthMaxMap = new HashMap<>();
    //    private static Set<EatPair<Class<? extends Animal>, Class<? extends Animal>>> pairs = getPair(Wolf.class, Rabbit.class);
    private static Map<String, String> stringPair = new HashMap<>();
    private static Map<Class<? extends Animal>, Integer> animalStartPopulation = new HashMap<>();
    static Map<Class<? extends Animal>, Integer> stepsMap = new HashMap<>();
    //Набор возможных путей поедания, строящийся на основе заполненной таблицы вероятности поедания. При поедании сначала будет поиск в ячейке такой комбинации
    //по голодному животному, если находит - то идем во вторую таблицу вероятности, и пытаемся покушать
    private static Map<String, List<String>> mapPairs;

    public static Map<Class<? extends Animal>, Integer> getAnimalStartPopulation() {
        return animalStartPopulation;
    }

    public static Map<String, List<String>> getMapPairs() {
        return mapPairs;
    }

    public static Map<Class<? extends Animal>, Integer> getStepsMap() {
        return stepsMap;
    }

    public static Map<Class<? extends Animal>, Integer> getHealthMaxMap() {
        return healthMaxMap;
    }
    //загрузка параметров
    {

        healthMaxMap = PropertiesLoader.loadPropertyMap("src/main/resources/maxhealth.properties");
        //загрузка из файла стартовой популяции каждого вида животных при инициализации
        animalStartPopulation = PropertiesLoader.loadPropertyMap("src/main/resources/animalstartpopulation.properties");
        //загрузка из файла максимального количества шагов за такт для животных из настройки
        stepsMap = PropertiesLoader.loadPropertyMap("src/main/resources/stepmap.properties");
        //загрузка из файла меппинга вероятностей поедания
        AnimalEatProbability.setProbabilityMap(PropertiesLoader.loadAnimalEatProbability("src/main/resources/animalEatProbability.properties"));
        // для первичной проверки возможности поедания в методе eat()
        mapPairs = AnimalEatProbability.getPairs();
    }

    public static class PropertiesLoader {
        //меппинг короткого имени класса с полным
        private static final Map<String, String> animalClassMap = new HashMap<>();
        private static int tickRate;

        public static int getTickRate() {
            return tickRate;
        }

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
        public static void loadIslandConfig () {
            Island island = null;
            Properties prop = new Properties();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("src/main/resources/islandconfig.properties");
                prop.load(fis);
                int width = Integer.parseInt(prop.getProperty("width"));
                int height = Integer.parseInt(prop.getProperty("height"));
                tickRate = Integer.parseInt(prop.getProperty("tickrate"));
                island = Island.getInstance(width, height);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public static Map<Class<? extends Animal>, Integer> loadPropertyMap(String filePath) {
            Map<Class<? extends Animal>, Integer> loadedMap = new HashMap<>();
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
        //для загрузки вероятности поедения животных
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



    public static class InitializeClass {
        static Island island = Island.getInstance();

        private static Map<Class<? extends Animal>, Integer> map = LoadClass.getAnimalStartPopulation();
        //рандомно заселяем остров согласно конфигурации animalstartpopulation
        public static void initialize() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            for (Map.Entry<Class<? extends Animal>, Integer> entry : map.entrySet()) {
                Class<? extends Animal> animalClass = entry.getKey();
                ThreadLocalRandom random = ThreadLocalRandom.current();

                int quantity = entry.getValue();
                for (int i = 0; i < quantity; i++) {
                    int xCoord = random.nextInt(0, island.getWidth());
                    int yCoord = random.nextInt(0, island.getHeight());
                    animalClass.getDeclaredConstructor(Cell.class).newInstance(island.getCellByCoordinates(xCoord,yCoord));

                }
            }
        }
    }

}

