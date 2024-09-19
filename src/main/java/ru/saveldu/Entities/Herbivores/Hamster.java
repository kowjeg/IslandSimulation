package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Hamster extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 2;
        stepSize =1;  // шаги за такт
    }
    public Hamster(Cell cell) {
        super(cell);
    }
}
