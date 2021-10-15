package Lesson_5.Home.System.Menu;


import Lesson_5.Home.System.Operator;

import java.io.Serializable;

public class OperationsMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Save" + "\n" +
                "2. Show Apartment list" + "\n" +
                "3. Add new Apartment" + "\n" +
                "4. Remove Apartment by id" + "\n" +
                "5. Find Info" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(Operator operator){
        int number = -1;
        while(true) {
            number = operator.readInt();
            if (number < 0 || number > 5) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> operator.toMainMenu();
            case 1 -> operator.save();
            case 2 -> operator.showList();
            case 3 -> operator.addNewApartment();
            case 4 -> operator.removeApartment(id(operator));
            case 5 -> operator.findInfo();
        }
    }
    public int id(Operator operator){
        System.out.println("Enter id:");
        int number = 0;
        while(true) {
            try{
                number = Integer.parseInt(operator.readLineAgain());
            } catch(Exception e){}
            if (number < 1) {
                System.out.println("Something wrong, try again.");
                System.out.println("Enter a id greater than 0");
            }
            else break;
        }
        return number;
    }
}