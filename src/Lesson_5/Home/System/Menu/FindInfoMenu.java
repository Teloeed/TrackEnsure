package Lesson_5.Home.System.Menu;


import Lesson_5.Home.System.Operator;

import java.io.Serializable;

public class FindInfoMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Find Apartments by rooms count = \"X\"" + "\n" +
                "2. Find Apartments by rooms count = \"X\" and on building floor(min-max)" + "\n" +
                "3. Sort Apartments by square" + "\n" +
                "4. Find Apartments by square more than \"X\"" + "\n" +
                "5. Sort Apartments by floors" + "\n" +
                "6. Show Apartments on floor \"X\"" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Operator operator){
        int number = -1;
        while(true) {
            number = operator.readInt();
            if (number < 0 || number > 6) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> operator.backToOperationsMenu();
            case 1 -> operator.findApartmentsByRoomsCount();
            case 2 -> operator.findApartmentsByRoomsCountAndFloor();
            case 3 -> operator.sortApartmentsBySquare();
            case 4 -> operator.findApartmentsBySquare();
            case 5 -> operator.sortApartmentsByFloors();
            case 6 -> operator.showApartmentsOnFloor();
        }
    }
}