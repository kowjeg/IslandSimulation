package ru.saveldu.Entities.Herbivores;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivore;

public class Sheep extends Herbivore {
    //инициализирую статик стату для всего класса
    static{
        maxHealth = 5;
        stepSize =3;  // шаги за такт
    }
    public Sheep(Cell cell) {
        super(cell);
    }
}
