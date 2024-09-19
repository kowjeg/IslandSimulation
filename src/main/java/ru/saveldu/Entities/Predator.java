package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Utils.AnimalEatProbability;
import ru.saveldu.Utils.ListUtils;
import ru.saveldu.Utils.LoadClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {

    public Predator(Cell cell) {
        super(cell);
        cell.addAnimal(this);
    }

    @Override
    public void eat() {

        ThreadLocalRandom random = ThreadLocalRandom.current();
        Class<? extends Predator> clazz = this.getClass();
        String className = clazz.getSimpleName();
        //список возможных жертв

        //список жертв(объектов) в ячейке
        List<Animal> victimList = new ArrayList<>();
        //получаем список возможных жертв
        List<String> victimProbList = LoadClass.getMapPairs().get(className);
        Animal victim;
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
            //фокус на рандомную цель, именно её будет пытаться съесть за один цикл
            victim = victimList.get(victimIndex);
            double chanceToEat = chanceToEatVictim(this, victim);
            canEat = random.nextDouble(1.0) < chanceToEat;
            if (canEat) {
                //едим жертву
                synchronized (cell.lock) {
                    cell.removeAnimal(victim);
                }
                if (health < maxHealth) health++;
            }
        }
        if (!canEat) health--;
        dieIfNoHealth();
    }

    private void dieIfNoHealth() {
        if (health <= 0) {
            this.die();
        }
    }

    private double chanceToEatVictim(Animal predator, Animal herbivore) {
        Class<? extends Animal> eater = predator.getClass();
        Class<? extends Animal> victim = herbivore.getClass();
        return AnimalEatProbability.getProbability(eater, victim);
    }
}
