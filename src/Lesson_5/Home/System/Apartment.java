package Lesson_5.Home.System;

import java.io.Serializable;
import java.util.*;

public class Apartment implements Serializable {
    private int id;
    private int apartmentNumber;
    private double apartmentSquare;
    private int floor;
    private int roomsCount;
    private String streetName;


    public Apartment() {}

    public Apartment createApartment(Operator operator){
        System.out.println("Please enter Apartment Number. 0 - Back to menu.");
        int apartmentNumber = readInt(operator);
        if(apartmentNumber == 0) {operator.nextStep(); return this;}

        System.out.println("Please enter Apartment Square. 0 - Back to menu.");
        double apartmentSquare = readDouble(operator);
        if(apartmentSquare == 0) {operator.nextStep(); return this;}

        System.out.println("Please enter Building floor. 0 - Back to menu.");
        int floor = readInt(operator);
        if(floor == 0) {operator.nextStep(); return this;}

        System.out.println("Please enter rooms count. 0 - Back to menu.");
        int roomsCount = readInt(operator);
        if(roomsCount == 0) {operator.nextStep(); return this;}

        System.out.println("Please enter street name. 0 - Back to menu.");
        String streetName = readString(operator);

        this.id = generateId(operator);
        this.apartmentNumber = apartmentNumber;
        this.apartmentSquare = apartmentSquare;
        this.floor = floor;
        this.roomsCount = roomsCount;
        this.streetName = streetName;

        return this;
    }

    private int readInt(Operator operator){
        int result = 0;
        while (true){
            try {
                result = Integer.parseInt(operator.readLineAgain());
                break;
            }
            catch (Exception e) {
                System.out.println("Please enter an integer. 0 - Back to menu.");
            }
        }
        return result;
    }
    private double readDouble(Operator operator){
        double result = 0;
        while (true) {
            try {
                result = Double.parseDouble(operator.readLineAgain());
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer or fractional number. 0 - Back to menu");
            }
        }
        return result;
    }

    private String readString(Operator operator){
        String result = null;
        while (true) {
            try {
                result = operator.readLineAgain();
                if (!result.matches("[a-zA-Z]*")) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Please enter a word without signs and numbers.");
            }
        }
        return result;
    }

    private int generateId(Operator operator){
        int id = 1;
        ArrayList<Apartment> tempList = operator.getApartments();
        if(tempList.size() > 0) {id = tempList.stream().max(Comparator.comparing(Apartment::getId)).get().getId() + 1;}
        return id;
    }

    public int getId() {return id;}
    public int getApartmentNumber() {return apartmentNumber;}
    public double getApartmentSquare() {return apartmentSquare;}
    public int getFloor() {return floor;}
    public int getRoomsCount() {return roomsCount;}
    public String getStreetName() {return streetName;}

    public void print(){
        System.out.println("["
                + "Id: " + id + "; "
                + "Apartment Number: " + apartmentNumber + "; "
                + "Apartment Square - " + apartmentSquare + "; "
                + "Building Floor - " + floor + "; "
                + "Rooms - " + roomsCount + "; "
                + "Street - " + streetName + "]");
    }
}
