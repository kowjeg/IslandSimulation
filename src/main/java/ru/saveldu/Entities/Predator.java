package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Utils.AnimalEatProbability;
import ru.saveldu.Utils.ListUtils;
import ru.saveldu.Utils.LoadClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    public Predator(Cell cell) {
        super(cell);
        cell.addPredator(this);
    }

    @Override
    public void eat() {

        ThreadLocalRandom random = ThreadLocalRandom.current();
        Class clazz = this.getClass();
        String className = clazz.getSimpleName();
        //список возможных жертв
        List<String> victimProbList = new ArrayList<>();
        //список жертв(объектов) в ячейке
        List<Animal> victimList = new ArrayList<>();
        //получаем список возможных жертв
        victimProbList = LoadClass.getMapPairs().get(className);
        Animal victim = null;
        //находим жертву в ячейке
        List<Animal> animalsCell = ListUtils.getAnimalsInCell(cell);
        //получаем список жертв, находящихся с хищником в одной ячейке
        for (Animal animal : animalsCell) {
            if (victimProbList.contains(animal.getClass().getSimpleName())) victimList.add(animal);
        }
        //может ли скушать? изменится если повезет поймать выбранную жертву.
        boolean canEat = false;
        if (!victimList.isEmpty()) {
            int max = victimList.size();
            int victimIndex = random.nextInt(max);
            victim = victimList.get(victimIndex);
            double chanceToEat = chanceToEatVictim(this, victim);
            canEat = random.nextDouble(1.0) < chanceToEat;
            if (canEat) {
                //едим жертву
                synchronized (cell.lock) {
                    cell.getHerbivores().remove(victim);
                }
                if (health < maxHealth) health++;
            }
        }
        if (!canEat) health--;
        if (health <= 0) {
            System.out.println("умер");
            this.die();
        }
    }

    private double chanceToEatVictim(Animal predator, Animal herbivore) {
        Class eater = predator.getClass();
        Class victim = herbivore.getClass();
        LoadClass loadClass = new LoadClass();
        return loadClass.getProbabilityTable().getProbability(eater, victim);
    }

    @Override
    public void die() {
        this.getCell().getPredators().remove(this);
        Animal.setCount(Animal.getCount() - 1);
    }
}
