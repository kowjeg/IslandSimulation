package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Caterpillar extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 3;
        stepSize =0;  // шаги за такт
    }
    public Caterpillar(Cell cell) {
        super(cell);
    }
}
