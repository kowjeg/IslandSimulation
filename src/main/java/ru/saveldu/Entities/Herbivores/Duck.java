package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Duck extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 2;
        stepSize =4;  // шаги за такт
    }
    public Duck(Cell cell) {
        super(cell);
    }
}
