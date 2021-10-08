package Lesson_3.Home8.Train;

import Lesson_3.Home8.Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Train {
    private int regularWagonCount;
    private int sleepingWagonCount;
    private int coupeWagonCount;
    private Locomotive locomotive;
    private ArrayList<Wagon> wagons;
    private ArrayList<Integer> wagonNumbers;


    public Train() {
        this.locomotive = new Locomotive();
        regularWagonCount = 0;
        sleepingWagonCount = 0;
        coupeWagonCount = 0;
        wagons = new ArrayList<>();
        wagonNumbers = new ArrayList<>();
    }

    public void addRegularWagon(Controller controller) {
        int count = newWagonCount(controller);
        if (count == 0) {
            controller.addWagon();
            return;
        }
        for (int i = 0; i < count; i++) {
            wagons.add(new RegularWagon(chooseNumber()));
            regularWagonCount++;
        }
        controller.nextStep();
    }
    public void removeRegularWagon(Controller controller){
        if (regularWagonCount!=0) {
            for (Wagon i : wagons) {
                if (i instanceof RegularWagon) {
                    wagons.remove(i);
                    break;
                }
            }
            regularWagonCount--;
        }
        else System.out.println("Нельзя удалить вагон, которого нет!");
        controller.nextStep();
    }

    public void addSleepingWagon(Controller controller) {
        int count = newWagonCount(controller);
        if (count == 0) {
            controller.addWagon();
            return;
        }
        for (int i = 0; i < count; i++) {
            wagons.add(new SleepingWagon(chooseNumber()));
            sleepingWagonCount++;
        }
        controller.nextStep();
    }
    public void removeSleepingWagon(Controller controller){
        if (sleepingWagonCount!=0) {
            for (Wagon i : wagons) {
                if (i instanceof SleepingWagon) {
                    wagons.remove(i);
                    break;
                }
            }
            sleepingWagonCount--;
        }
        else System.out.println("Нельзя удалить вагон, которого нет!");
        controller.nextStep();
    }

    public void addCoupeWagon(Controller controller) {
        int count = newWagonCount(controller);
        if (count == 0) {
            controller.addWagon();
            return;
        }
        for (int i = 0; i < count; i++) {
            wagons.add(new CoupeWagon(chooseNumber()));
            coupeWagonCount++;
        }
        controller.nextStep();
    }
    public void removeCoupeWagon(Controller controller){
        if (coupeWagonCount!=0) {
            for (Wagon i : wagons) {
                if (i instanceof CoupeWagon) {
                    wagons.remove(i);
                    break;
                }
            }
            coupeWagonCount--;
        }
        else System.out.println("Нельзя удалить вагон, которого нет!");
        controller.nextStep();
    }


    private int chooseNumber(){
        int number = 1;
        int listSize = wagonNumbers.size();
        if (listSize != 0){
            Collections.sort(wagonNumbers);
            for(int i = 0; i < listSize; i++){
                if(wagonNumbers.get(i) != i+1) {
                    number = i + 1;
                    break;
                }
            }
            if (number == 1) number = listSize+1;
        }
        wagonNumbers.add(number);
        Collections.sort(wagonNumbers);
        return number;
    }

    private int newWagonCount(Controller controller){
        System.out.println("__________________________________");
        System.out.println("Enter Wagon count. 0 - back.");
        System.out.println("__________________________________");
        int count = controller.readInt();
        if (count >= 0) return count;
        System.out.println("Введите положительное число!");
        newWagonCount(controller);
        return count;
    }

    public ArrayList<Wagon> getWagons() {return wagons;}
    public ArrayList<Integer> getWagonNumbers() {return wagonNumbers;}
    public Locomotive getLocomotive() {return locomotive;}


    public void showTotalPassengers(Controller controller) {
        int totalPassengers = 0;
        for(Wagon i : wagons) totalPassengers += i.getPassengers();
        System.out.println("Total Passengers: " + totalPassengers);
        controller.nextStep();
    }
    public void showTotalPrice(Controller controller) {
        double totalPrice = 0;
        for(Wagon i : wagons) totalPrice += i.getPrice();
        DecimalFormat df=new DecimalFormat("0.00");
        System.out.println("Total Price: " + df.format(totalPrice) + "грн.");
        controller.nextStep();
    }
    public void showTotalBaggage(Controller controller) {
        int totalBaggage = 0;
        for(Wagon i : wagons) totalBaggage += i.getBaggage();
        System.out.println("Total Baggage: " + totalBaggage);
        controller.nextStep();
    }
    public void sortWagons(Comparator comparator){
        wagons.sort(comparator);
    }
    public void sortWagonsReversed(Comparator comparator){
        wagons.sort(comparator.reversed());
    }

    public void findPassengersCount(Controller controller){
        int min = minMax(controller);
        if (min == 0) {
            controller.findInfo();
            return;
        }
        int max = minMax(controller);
        if (max == 0) {
            controller.findInfo();
            return;
        }
        while (max <= min) {
            System.out.println("Max must be bigger than min! Try to enter new max value! 0 - back.");
            max = minMax(controller);
            if (max == 0) {
                controller.findInfo();
                return;
            }
        }
        for (Wagon i : wagons){
            if (i.getPassengers() > min && i.getPassengers() < max) i.print();
        }
        controller.nextStep();
    }
    public void findPrice(Controller controller){
        double min = minMax(controller);
        if (min == 0) {
            controller.findInfo();
            return;
        }
        double max = minMax(controller);
        if (max == 0) {
            controller.findInfo();
            return;
        }
        while (max <= min) {
            System.out.println("Max must be bigger than min! Try to enter new max value! 0 - back.");
            max = minMax(controller);
            if (max == 0) {
                controller.findInfo();
                return;
            }
        }
        for (Wagon i : wagons){
            if (i.getPrice() > min && i.getPrice() < max) i.print();
        }
        controller.nextStep();
    }

    public void findBaggageCount(Controller controller){
        int min = minMax(controller);
        if (min == 0) {
            controller.findInfo();
            return;
        }
        int max = minMax(controller);
        if (max == 0) {
            controller.findInfo();
            return;
        }
        while (max <= min) {
            System.out.println("Max must be bigger than min! Try to enter new max value! 0 - back.");
            max = minMax(controller);
            if (max == 0) {
                controller.findInfo();
                return;
            }
        }
        for (Wagon i : wagons){
            if (i.getBaggage() > min && i.getBaggage() < max) i.print();
        }
        controller.nextStep();
    }
    public void findComfortClass(Controller controller){
        int number = minMax(controller);
        if (number == 0) {
            controller.findInfo();
            return;
        }
        while (number < 1 || number > 3) {
            System.out.println("Enter class 1-3! 0 - back.");
            number = minMax(controller);
            if (number == 0) {
                controller.findInfo();
                return;
            }
        }
        for (Wagon i : wagons){
            if (i.getComfortClass() == number) i.print();
        }
        controller.nextStep();
    }

    private int minMax(Controller controller){
        System.out.println("__________________________________");
        System.out.println("Enter number. 0 - back.");
        System.out.println("__________________________________");
        int count = controller.readInt();
        if (count >= 0) return count;
        System.out.println("Введите положительное число!");
        newWagonCount(controller);
        return count;
    }
}
