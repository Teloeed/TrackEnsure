package Lesson_3.Home8.Menu;

import Lesson_3.Home8.Controller;

public class MainMenu implements Menu{
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Exit" + "\n" +
                "1. Create new Train" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Controller controller){
        int number = controller.readInt();
        if (number < 0 || number > 1) {
            System.out.println("Something wrong, try again.");
            print();
            choice(controller);
            return;
        }
        switch (number) {
            case 0 -> controller.exit();
            case 1 -> controller.createTrain();
        }
    }
}
