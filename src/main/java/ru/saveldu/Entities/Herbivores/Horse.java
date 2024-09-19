package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Horse extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 8;
        stepSize =4;  // шаги за такт
    }
    public Horse(Cell cell) {
        super(cell);
    }
}
