package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Buffalo extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 6;
        stepSize =3;  // шаги за такт
    }
    public Buffalo(Cell cell) {
        super(cell);
    }
}
