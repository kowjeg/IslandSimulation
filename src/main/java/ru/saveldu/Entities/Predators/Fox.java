package ru.saveldu.Entities.Predators;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Predator;

public class Fox extends Predator {

    //инициализирую статик стату для всего класса
    {
        stepSize =2;  // шаги за такт
    }

    public Fox(Cell cell) {
        super(cell);
    }
}
