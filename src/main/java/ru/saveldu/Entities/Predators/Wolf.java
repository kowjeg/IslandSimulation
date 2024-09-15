package ru.saveldu.Entities.Predators;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Predator;

public class Wolf extends Predator {

    //инициализирую статик стату для всего класса
    {
        stepSize =3;
    }
    private static int count = 0;

    public Wolf(Cell cell) {
        super(cell);
        count++;
    }
    public static int getCount () {
        return count;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A";
    }

    @Override
    public void die() {
        super.die();
        count--;

    }
}
