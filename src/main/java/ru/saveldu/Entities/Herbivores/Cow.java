package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Cow extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 8;
        stepSize =2;  // шаги за такт
    }
    public Cow(Cell cell) {
        super(cell);
    }
}
