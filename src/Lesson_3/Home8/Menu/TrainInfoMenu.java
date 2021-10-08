package Lesson_3.Home8.Menu;

import Lesson_3.Home8.Controller;

public class TrainInfoMenu implements Menu{
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Show All Passengers" + "\n" +
                "2. Show total price for Train passengers" + "\n" +
                "3. Show total baggage" + "\n" +
                "4. Sort and show train wagons by passengers count" + "\n" +
                "5. Sort and show train wagons by total baggage" + "\n" +
                "6. Sort and show train wagons by total price" + "\n" +
                "7. Sort and show train wagons by comfort class" + "\n" +
                "8. Sort and show train wagons by wagon №" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Controller controller){
        int number = controller.readInt();
        if (number < 0 || number > 8) {
            System.out.println("Something wrong, try again.");
            print();
            choice(controller);
            return;
        }
        switch (number) {
            case 0 -> controller.toMainMenu();
            case 1 -> controller.showTotalPassengers();
            case 2 -> controller.showTotalPrice();
            case 3 -> controller.showTotalBaggage();
            case 4 -> controller.sortByPassengers();
            case 5 -> controller.sortByBaggage();
            case 6 -> controller.sortByPrice();
            case 7 -> controller.sortByComfort();
            case 8 -> controller.sortByWagonNumber();
        }
    }
}