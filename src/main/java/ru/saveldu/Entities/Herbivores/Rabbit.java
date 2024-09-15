package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Rabbit extends Herbivore {



    {
        stepSize = 1;

    }
    public Rabbit(Cell cell) {
        super(cell);

    }

    @Override
    public String toString() {
        return "🐇";
    }
}
