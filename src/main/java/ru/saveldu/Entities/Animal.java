package ru.saveldu.Entities;

import ru.saveldu.Cell;
import ru.saveldu.Island;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Animal extends AbstractOrganism {

    protected int stepSize;

    private static int count;

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Animal.count = count;
    }

    public Animal(Cell cell) {
        super(cell);
        count++;
    }

    public void move() {
        int currentX = cell.getX();
        int currentY = cell.getY();

        int newXCoord = currentX;
        int newYCoord = currentY;
        Cell temporaryCell = new Cell(newXCoord, newYCoord);

        // делаем несколько шагов (stepSize) за один такт
        for (int i = 0; i < this.stepSize; i++) {
            boolean isChosen = false;

            // выбираем направление, пока не выдаст новую ячейку с координатами внутри острова
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

            // обновляем текущие координаты и ячейку

            temporaryCell = new Cell(newXCoord, newYCoord);
        }


        Cell newCell = Island.getInstance().getCellByCoordinates(temporaryCell.getX(), temporaryCell.getY());
        // проверяем в какой список добавить и из какого убрать объект хищника или травоядного, тут мне кажется не оптимально, можно как-то выше абстракцию
        //наверно добавить, но я не понял как в таком случае вести списки хищников и травоядных в разрезе ячейки
        if (this instanceof Predator) {
            newCell.getPredators().add((Predator) this);
            this.getCell().getPredators().remove(this);
        } else {
            newCell.getHerbivores().add((Herbivore) this);
            this.getCell().getHerbivores().remove(this);
        }
        this.cell = newCell;


    }

    public Direction chooseDirection() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int numberOfDirections = Direction.values().length;
        return Direction.values()[random.nextInt(numberOfDirections)];
    }

    public boolean checkOutOfBounds(int x, int y) {
        Island island = Island.getInstance();
        int islandWidth = island.getWidth();
        int islandHeight = island.getHeight();

        return x >= 0 && x < islandWidth && y >= 0 && y < islandHeight;
    }

    public abstract void eat();

    public void reproduce(Cell cell, Class<Animal> animalClass) {

        Island island = Island.getInstance();
        Cell cellByCoordinates = island.getCellByCoordinates(cell.getX(), cell.getY());
        List<Animal> animalList = new ArrayList<>(cell.getHerbivores());
        animalList.addAll(cell.getPredators());


    }

    public abstract void die();

    public <T extends Animal> List<T> filterByType(List<Animal> animals) {
        List<T> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (this.getClass().isInstance(animal)) {
                result.add((T) animal); // Приведение типа
            }
        }
        return result;
    }


}
