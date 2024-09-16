package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Utils.AnimalEatProbability;
import ru.saveldu.Utils.ListUtils;
import ru.saveldu.Utils.LoadClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Predator extends Animal {


    public Predator(Cell cell) {
        super(cell);
        cell.addPredator(this);
    }

    @Override
    public void eat() {
        Random random = new Random();
        Class clazz = this.getClass();
        String className = clazz.getSimpleName();
        List<String> victimList = new ArrayList<>();
//        LoadClass loadClass = new LoadClass();
//        var map = LoadClass.getMapPairs();
//        System.out.println("test");
        //получаем список возможных жертво
        victimList  = LoadClass.getMapPairs().get(className);
        Animal victim = null;
        //находим жертву в ячейке
        List<Animal> animalsCell = ListUtils.getAnimalsInCell(cell);
        for (Animal animal : animalsCell) {
            if (victimList.contains(animal.getClass().getSimpleName())) victim = animal;
            break;
        }
        double chanceToEat= chanceToEatVictim(this, victim);



        System.out.println(victimList);
//        if (!cell.getHerbivores().isEmpty()) {
//            Animal victim = cell.getHerbivores().get(0);
//            double chanceToEat = chanceToEatVictim(this, victim);
//            boolean canEat = random.nextDouble(1.0) < chanceToEat;
//            if (canEat) {
//                cell.getHerbivores().remove(victim);
//
//                health++;
//            } else {
//                health--;
//            }
//
//        }
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
