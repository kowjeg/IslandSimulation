package ru.saveldu;

import ru.saveldu.Entities.Animal;
import ru.saveldu.Entities.Herbivore;
import ru.saveldu.Entities.Plant;
import ru.saveldu.Entities.Predator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cell {
    private List<Predator> predators= new ArrayList<>();
    private List<Herbivore> herbivores =  new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();

    public void removePlant (Plant plant) {
        plants.remove(plant);
    }
    public void addPlant (List<Plant> plantList) {
        plants.addAll(plantList);
    }

    public List<Plant> getPlants() {
        return plants;
    }

    private static Map<Animal,Integer> animalMaxInCell= new HashMap<>();
    // max population animals in cell
    public static void setAnimalMaxInCell(Map<Animal, Integer> animalMaxInCell) {
        Cell.animalMaxInCell = animalMaxInCell;
    }
    //map with current population animals in cell
    private Map<Class<? extends Animal>, Integer> currAnimalInCell = new HashMap<>();

    public static Map<Animal, Integer> getAnimalMaxInCell() {
        return animalMaxInCell;
    }

    public final Object lock = new Object();

    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Cell getCellByCoords(Island island,int x, int y) {
        return island.getCells()[x][y];
    }

    public Cell getRandomCell () {
        return null;
    }


    public void addAnimal (Animal animal) {
        if (animal instanceof Predator) {
            predators.add((Predator) animal);
        }
        else if (animal instanceof Herbivore) {
            herbivores.add((Herbivore) animal);
        }
    }
    public void removeAnimal (Animal animal) {
        if (animal instanceof Predator) {
            predators.remove(animal);
        } else if (animal instanceof Herbivore) {
            herbivores.remove(animal);
        }
    }


    public List<Predator> getPredators() {

        return predators;
    }
    public List<Herbivore> getHerbivores() {

        return herbivores;
    }

    public int getPredatorCount() {

        return predators.size();
    }
    public int getHerbivoreCount() {

        return herbivores.size();
    }


    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public String toString() {
        return getHerbivores().toString() + " " + getPredators().toString();
    }
}
