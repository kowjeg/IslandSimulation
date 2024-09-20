package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Kangaroo extends Herbivore {

    private static int count = 0;

    public Kangaroo(Cell cell) {
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
        return "\uD83E\uDD98";
    }

    public static String getImage() {
        return "\uD83E\uDD98";
    }
}
