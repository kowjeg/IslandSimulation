package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Kangaroo extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 4;
        stepSize =5;  // шаги за такт
    }
    public Kangaroo(Cell cell) {
        super(cell);
    }
}
