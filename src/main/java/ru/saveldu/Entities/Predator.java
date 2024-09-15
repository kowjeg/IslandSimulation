package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Utils.LoadClass;

import java.util.Random;

public abstract class Predator extends Animal {


    public Predator(Cell cell) {
        super(cell);
        cell.addPredator(this);
    }

    @Override
    public void eat() {
        Random random = new Random();

        if (!cell.getHerbivores().isEmpty()) {
            Animal victim = cell.getHerbivores().get(0);
            double chanceToEat = chanceToEatVictim(this, victim);
            boolean canEat = random.nextDouble(1.0) < chanceToEat;
            if (canEat) {
                cell.getHerbivores().remove(victim);

                health++;
            } else {
                health--;
            }

        }
        //если здоровья стало 0 - умираем
            if (health <= 0) {
                this.die();
            }
        }

    private double chanceToEatVictim(Animal predator, Animal herbivore) {
        Class clazz1 = predator.getClass();
        Class clazz2 = herbivore.getClass();
        LoadClass loadClass = new LoadClass();
        return loadClass.getProbabilityTable().getProbability(clazz1,clazz2);
    }

    @Override
    public void die() {
        this.getCell().getPredators().remove(this);
        Animal.setCount(Animal.getCount() - 1);
    }
}
