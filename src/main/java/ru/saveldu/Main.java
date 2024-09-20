package ru.saveldu;
import ru.saveldu.Utils.*;
import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String[] args) throws InterruptedException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Hello Island!");


        LoadClass loadClass = new LoadClass();
        LoadClass.PropertiesLoader.loadIslandConfig();
        LoadClass.InitializeClass.initialize();
        StatClass.printMap(Island.getInstance().getCells());
        StepClass stepClass = new StepClass();
        stepClass.run();

    }
}