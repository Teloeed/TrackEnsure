package Lesson_3.Home8.Menu;

import Lesson_3.Home8.Controller;

public class FindInfoMenu implements Menu{
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Find passengers count (between min and max)" + "\n" +
                "2. Find price (between min and max)" + "\n" +
                "3. Find baggage count (between min and max)" + "\n" +
                "4. Find wagons by comfort class" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Controller controller){
        int number = controller.readInt();
        if (number < 0 || number > 4) {
            System.out.println("Something wrong, try again.");
            print();
            choice(controller);
            return;
        }
        switch (number) {
            case 0 -> controller.toMainMenu();
            case 1 -> controller.findPassengersCount();
            case 2 -> controller.findPrice();
            case 3 -> controller.findBaggageCount();
            case 4 -> controller.findComfortClass();
        }
    }
}