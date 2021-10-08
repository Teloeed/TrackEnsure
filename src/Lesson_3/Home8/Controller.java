package Lesson_3.Home8;

import Lesson_3.Home8.Menu.*;
import Lesson_3.Home8.Train.Train;
import Lesson_3.Home8.Train.Wagon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Controller {
    private BufferedReader reader;
    public Controller controller;
    private Train train;
    private final MainMenu mainMenu;
    private final TrainMenu trainMenu;
    private final AddWagonsMenu addWagonsMenu;
    private final RemoveWagonsMenu removeWagonsMenu;
    private final TrainInfoMenu trainInfoMenu;
    private final FindInfoMenu findInfoMenu;
    private Menu menu;

    public Controller() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        controller = this;
        mainMenu = new MainMenu();
        trainMenu = new TrainMenu();
        addWagonsMenu = new AddWagonsMenu();
        removeWagonsMenu = new RemoveWagonsMenu();
        trainInfoMenu = new TrainInfoMenu();
        findInfoMenu = new FindInfoMenu();
        menu = mainMenu;
    }
    public Train getTrain() {return train;}

    public int readInt(){
        int result = 0;
        try {
            result = Integer.parseInt(reader.readLine());
        }
        catch (Exception e) {
            System.out.println("Something wrong, try again.");
            menu.print();
            readInt();
        }
        return result;
    }


    public void launch() {
        nextStep();
    }
    public void exit(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void toMainMenu(){
        menu = trainMenu;
        nextStep();
    }
    public void nextStep(){
        menu.print();
        menu.choice(controller);
    }
    /**menu methods**/
    public void createTrain(){
        train = new Train();
        menu = trainMenu;
        nextStep();
    }

    public void showTrain() {
        train.getLocomotive().print();
        train.getWagons().forEach(Wagon::print);
        nextStep();
    }

    public void addWagon() {
        menu = addWagonsMenu;
        nextStep();
    }

    public void removeWagon() {
        menu = removeWagonsMenu;
        nextStep();
    }

    public void trainInfo(){
        menu = trainInfoMenu;
        nextStep();
    }
    public void findInfo(){
        menu = findInfoMenu;
        nextStep();
    }

    public void showTotalPassengers(){
        train.showTotalPassengers(controller);
    }
    public void showTotalPrice(){
        train.showTotalPrice(controller);
    }
    public void showTotalBaggage(){
        train.showTotalBaggage(controller);
    }
    public void sortByPassengers(){
        train.sortWagonsReversed(Comparator.comparing(Wagon::getPassengers));
        showTrain();
    }
    public void sortByBaggage(){
        train.sortWagonsReversed(Comparator.comparing(Wagon::getBaggage));
        showTrain();
    }
    public void sortByPrice(){
        train.sortWagonsReversed(Comparator.comparing(Wagon::getPrice));
        showTrain();
    }
    public void sortByComfort(){
        train.sortWagons(Comparator.comparing(Wagon::getComfortClass));
        showTrain();
    }
    public void sortByWagonNumber(){
        train.sortWagons(Comparator.comparing(Wagon::getNumber));
        showTrain();
    }
    public void findPassengersCount(){
        train.findPassengersCount(controller);
    }
    public void findPrice(){
        train.findPrice(controller);
    }
    public void findBaggageCount() {
        train.findBaggageCount(controller);
    }
    public void findComfortClass(){
        train.findComfortClass(controller);
    }

}
