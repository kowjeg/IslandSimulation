package ru.saveldu;

import ru.saveldu.Entities.Herbivore;
import ru.saveldu.Entities.Plant;
import ru.saveldu.Entities.Predator;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Predator> predators= new ArrayList<>();
    private List<Herbivore> herbivores =  new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();

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


    public void addHerbivore(Herbivore herbivore) {
        herbivores.add(herbivore);
    }
    public void addPredator(Predator predator) {
        predators.add(predator);
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
