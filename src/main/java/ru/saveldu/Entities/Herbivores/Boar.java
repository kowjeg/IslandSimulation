package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Boar extends Herbivore {
    //инициализирую статик стату для всего класса
    static{
        maxHealth=5;
        stepSize =2;  // шаги за такт
    }
    public Boar(Cell cell) {
        super(cell);
    }
}
