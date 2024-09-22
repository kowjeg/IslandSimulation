package ru.saveldu.Utils;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Grass;
import ru.saveldu.Entities.Plant;
import ru.saveldu.Island;

import java.util.ArrayList;
import java.util.List;

public class PlantsGenerator implements Runnable {

    List<Plant> plants;
    static Cell[][] cells = Island.getInstance().getCells();
    @Override
    public void run() {
        while (!Island.getInstance().isGameOver()) {
            getPlantsToIsland();
            try {
                Thread.sleep(LoadClass.PropertiesLoader.getTickRate());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void getPlantsToIsland() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].addPlant(getPlants());
            }
        }
    }

    private List<Plant> getPlants() {
        List<Plant> plants = new ArrayList<Plant>();
        for (int i = 0; i < 10; i++) {
            plants.add(new Grass());
        }
        return plants;
    }
    public static int getPlantCount () {
        int plantCount = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                plantCount = plantCount + cells[i][j].getPlants().size();
            }
        }
        return (plantCount/cells.length)/cells[0].length;
    }
}
