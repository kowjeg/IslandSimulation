package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Island;
import ru.saveldu.Utils.AnimalFactory;
import ru.saveldu.Utils.Direction;
import ru.saveldu.Utils.ListUtils;
import ru.saveldu.Utils.LoadClass;


import java.util.List;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends AbstractOrganism {
    private int age;  //Возраст в тактах. Животное сможет умирать от старости
    private static int maxAge; //предельный возраст для каждого вида животного
    private boolean isReprodusable;
    protected static int maxHealth;
    private static int count;
    protected static int stepSize;
    protected int health;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Animal.count = count;
    }

    public Animal(Cell cell) {
        super(cell);
        count++;
        maxHealth = LoadClass.getHealthMaxMap().get(this.getClass());
        stepSize = LoadClass.getStepsMap().get(this.getClass());
        health = maxHealth;
        isReprodusable = false;
    }

    //каждый 4 такт можно размножаться
    private void resetReproduce() {
        age++;
        if (age % 4 == 0) isReprodusable = true;
    }

    public void move() {
        resetReproduce();
        int newXCoord = cell.getX();
        int newYCoord = cell.getY();
        // делаем несколько шагов за один такт
        for (int i = 0; i < this.stepSize; i++) {
            boolean isChosen = false;
            while (!isChosen) {
                Direction direction = chooseDirection();
                int tempX = newXCoord;
                int tempY = newYCoord;
                // определяем изменение координат в зависимости от направления
                switch (direction) {
                    case UP:
                        tempY--;
                        break;
                    case DOWN:
                        tempY++;
                        break;
                    case LEFT:
                        tempX--;
                        break;
                    case RIGHT:
                        tempX++;
                        break;
                    case STAY:
                        isChosen = true; // остаемся на месте
                        break;
                }
                // проверяем, что новые координаты находятся внутри границ
                if (checkOutOfBounds(tempX, tempY)) {
                    newXCoord = tempX;
                    newYCoord = tempY;
                    isChosen = true;
                }
            }
        }
        // получаем ссылку на ячейку с новыми проверенными координатами, куда переместим животное и перемещаем его, затем меняем у животного ссылку на ячейку
        Cell newCell = Island.getInstance().getCellByCoordinates(newXCoord, newYCoord);
        newCell.addAnimal(this);
        cell.removeAnimal(this);
        this.cell = newCell;
    }

    public Direction chooseDirection() {
        ThreadLocalRandom random = ThreadLocalRandom.current();


        int numberOfDirections = Direction.values().length;
        return Direction.values()[random.nextInt(numberOfDirections)];
    }

    public boolean checkOutOfBounds(int x, int y) {
        Island island = Island.getInstance();
        int islandWidth = island.getWidth();
        int islandHeight = island.getHeight();
        // true если вписались в сетку, false если вылетели за пределы острова
        return x >= 0 && x < islandWidth && y >= 0 && y < islandHeight;
    }

    public abstract void eat();

    //получаем список партнеров того же вида из клетки, ищем любого кто готов размножиться, если находим то размножаем и выходим из цикла.
    public void reproduce() {
        if (this.isReprodusable) {
            List<Animal> partnerList = ListUtils.partnersListInCell(this, cell);

            if (partnerList.size() > 0) {
                for (Animal animal : partnerList) {
                    if (animal.isReprodusable) {
                        Animal partner = animal;
                        partner.isReprodusable = false;
                        this.isReprodusable = false;
                        AnimalFactory.createAnimal(cell, this.getClass());
                        break;
                    }
                }
            }
        }
    }

    public void die() {
        Animal.setCount(Animal.getCount() - 1);
        isAlive = false;
        cell.removeAnimal(this);
    }

    public void dieIfNoHealth() {
        if ((health <= 0 || age>25) && isAlive) {
            this.die();
        }
    }
}
