package ru.saveldu;
import ru.saveldu.Entities.Herbivores.Rabbit;
import ru.saveldu.Utils.*;
import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Hello Island!");


        LoadClass loadClass = new LoadClass();
        LoadClass.PropertiesLoader.loadIslandConfig();
        LoadClass.InitializeClass.initialize();
        StatClass.printMap(Island.getInstance().getCells());
        Cell[][] cells = Island.getInstance().getCells();

        Thread thread = new Thread(new PlantsGenerator());
        thread.start();
        StepClass stepClass = new StepClass();

        stepClass.run();


    }
}