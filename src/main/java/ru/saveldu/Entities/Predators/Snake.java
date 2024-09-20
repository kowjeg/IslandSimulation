package ru.saveldu.Entities.Predators;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Predator;

public class Snake extends Predator {

    private static int count = 0;

    public Snake(Cell cell) {
        super(cell);
        count++;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public void die() {
        count--;
        super.die();
    }

    @Override
    public String toString() {
        return "\uD83D\uDC0D";
    }

    public static String getImage() {
        return "\uD83D\uDC0D";
    }
}
