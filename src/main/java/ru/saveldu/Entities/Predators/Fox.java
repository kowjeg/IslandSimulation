package ru.saveldu.Entities.Predators;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Predator;

public class Fox extends Predator {


    private static int count = 0;

    public Fox(Cell cell) {
        super(cell);
        count++;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void die() {
        super.die();
        count--;

    }

    @Override
    public String toString() {
        return "\uD83E\uDD8A";
    }

    public static String getImage() {
        return "\uD83D\uDD8A";
    }
}
