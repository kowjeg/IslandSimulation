package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Deer extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 7;
        stepSize =4;  // шаги за такт
    }
    public Deer(Cell cell) {
        super(cell);
    }
}
