package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class GuineaPig extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 2;
        stepSize =1;  // шаги за такт
    }
    public GuineaPig(Cell cell) {
        super(cell);
    }
}
