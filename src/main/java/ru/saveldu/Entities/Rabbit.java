package ru.saveldu.Entities;

import ru.saveldu.Cell;

public class Rabbit extends Herbivore{



    public Rabbit(Cell cell) {
        super(cell);

    }


    @Override
    public void reproduce() {
        this.getCell().addHerbivore(new Rabbit(this.getCell()));

    }

    @Override
    public String toString() {
        return "🐇";
    }
}
