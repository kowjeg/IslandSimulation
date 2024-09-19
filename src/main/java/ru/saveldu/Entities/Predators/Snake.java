package ru.saveldu.Entities.Predators;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Predator;

public class Snake extends Predator {
    //инициализирую статик стату для всего класса
    {
        stepSize =1;  // шаги за такт
    }
    public Snake(Cell cell) {
        super(cell);
    }
}
