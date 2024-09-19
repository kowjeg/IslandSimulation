package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Goat extends Herbivore {
    //инициализирую статик стату для всего класса
    static {
        maxHealth = 4;
        stepSize =3;  // шаги за такт
    }
    public Goat(Cell cell) {
        super(cell);
    }
}
