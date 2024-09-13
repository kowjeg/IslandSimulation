package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Rabbit extends Herbivore {




    public Rabbit(Cell cell) {
        super(cell);

    }

    @Override
    public String toString() {
        return "🐇";
    }
}
