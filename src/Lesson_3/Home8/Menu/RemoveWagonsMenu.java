package Lesson_3.Home8.Menu;

import Lesson_3.Home8.Controller;

public class RemoveWagonsMenu implements Menu{
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Regular Wagon" + "\n" +
                "2. Sleeping Wagon" + "\n" +
                "3. Coupe Wagon" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Controller controller){
        int number = controller.readInt();
        if (number < 0 || number > 3) {
            System.out.println("Something wrong, try again.");
            print();
            choice(controller);
            return;
        }
        switch (number) {
            case 0 -> controller.toMainMenu();
            case 1 -> controller.getTrain().removeRegularWagon(controller);
            case 2 -> controller.getTrain().removeSleepingWagon(controller);
            case 3 -> controller.getTrain().removeCoupeWagon(controller);
        }
    }
}