package ru.saveldu.Entities.Predators;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Predator;

public class Eagle extends Predator {
    //инициализирую статик стату для всего класса
    {
        stepSize =3;  // шаги за такт
    }
    public Eagle(Cell cell) {
        super(cell);
    }
}
