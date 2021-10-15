package Lesson_5.Home.System.Menu;


import Lesson_5.Home.System.Operator;

import java.io.Serializable;

public class MainMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Exit" + "\n" +
                "1. Load Apartments list" + "\n" +
                "2. To Apartments list" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Operator operator){
        int number = -1;
        while(true) {
            number = operator.readInt();
            if (number < 0 || number > 2) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> operator.exit();
            case 1 -> operator.load();
            case 2 -> operator.toOperationsMenu();
        }
    }
}
