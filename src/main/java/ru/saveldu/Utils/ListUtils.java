package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivore;
import ru.saveldu.Entities.Predator;
import ru.saveldu.Island;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
    final private static Island island = Island.getInstance();

    public static <T> List<T> filterByClass(List<? extends Animal> predators, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for (Animal animal : predators) {
            if (clazz.isInstance(animal)) {
                result.add(clazz.cast(animal));
            }
        }
        return result;
    }
    public static List <Animal> getAllAnimals () {

        List<Animal> animalList = new ArrayList<>();
        Cell[][] cells = island.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {

                animalList.addAll(cells[i][j].getHerbivores());
                animalList.addAll(cells[i][j].getPredators());
            }
        }
        return animalList;
    }

    public static List <Animal> getAnimalsInCell (Cell cell) {

        List<Animal> animalList = new ArrayList<>();
        List<Herbivore> herbivoreList = cell.getHerbivores();
        List<Predator> predatorList = cell.getPredators();
        animalList.addAll(herbivoreList);
        animalList.addAll(predatorList);
        return animalList;
    }

}
