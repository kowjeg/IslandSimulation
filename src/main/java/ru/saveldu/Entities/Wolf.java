package ru.saveldu.Entities;

import ru.saveldu.Cell;

import java.util.Random;

public class Wolf extends Predator{
    public static String imageText = "WOLF";
    public Wolf(Cell cell) {
        super(cell);

    }

    @Override
    public void move(Cell cell) {

    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }

    @Override
    public void eat() {
        Random random = new Random();

        if (!cell.getHerbivores().isEmpty() && random.nextInt(100) > 60) {
            Herbivore victim = cell.getHerbivores().remove(0);
            System.out.println(this.toString() + " съел " + victim.toString());
        }
        else {
            System.out.println(this.toString() + " умер от голода");
            this.die();
        }

    }
}
