package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Island;

import java.util.Random;

public abstract class Predator extends Animal {



    public Predator(Cell cell) {
        super(cell);
        cell.addPredator(this);
    }
    public void move(Island island) {
        int min = getMinXCoordinate();
        int max = getMaxXCoordinate(island);

        Random random = new Random();
        int newXCoord = random.nextInt((max - min) + 1) + min;
        System.out.println(newXCoord);

        this.getCell().getPredators().remove(this);
        Cell newCell = island.getCells()[newXCoord][this.getCell().getY()];
        newCell.getPredators().add(this);
        this.cell = newCell;

    }

    private int getMinXCoordinate() {
        if (cell.getX() == 0) {
            return 0;
        } else {
            return cell.getX() - 1;
        }
    }

    private int getMaxXCoordinate(Island island) {
        int width = island.getCells().length - 1;
        if (width == cell.getX()) {
            return cell.getX();
        } else {
            return cell.getX() + 1;
        }
    }


    @Override
    public void eat() {
        Random random = new Random();

        if (!cell.getHerbivores().isEmpty() && random.nextInt(100) > 60) {
            Herbivore victim = cell.getHerbivores().remove(0);
            System.out.println(this.toString() + " съел " + victim.toString());
        } else {
            System.out.println(this.toString() + " умер от голода");
            this.die();
        }

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void die() {
        this.getCell().getPredators().remove(this);
    }
}
