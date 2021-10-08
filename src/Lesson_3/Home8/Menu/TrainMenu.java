package Lesson_3.Home8.Menu;

import Lesson_3.Home8.Controller;

public class TrainMenu implements Menu{
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Exit" + "\n" +
                "1. Show Train" + "\n" +
                "2. Add Wagon" + "\n" +
                "3. Remove Wagon" + "\n" +
                "4. Train Info" + "\n" +
                "5. Find Info" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Controller controller){
        int number = controller.readInt();
        if (number < 0 || number > 5) {
            System.out.println("Something wrong, try again.");
            print();
            choice(controller);
            return;
        }
        switch (number) {
            case 0 -> controller.exit();
            case 1 -> controller.showTrain();
            case 2 -> controller.addWagon();
            case 3 -> controller.removeWagon();
            case 4 -> controller.trainInfo();
            case 5 -> controller.findInfo();
        }
    }
    public int count(Controller controller){
        System.out.println("Write wagon count:");
        int number = controller.readInt();
        if (number < 1) {
            System.out.println("Something wrong, try again.");
            System.out.println("Write wagon count:");
            count(controller);
        }
        return number;
    }
}