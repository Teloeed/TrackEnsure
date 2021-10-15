package Lesson_5.Home.System;

import Lesson_5.Home.System.Menu.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Operator implements Serializable {
    Operator operator;
    private ArrayList<Apartment> apartments;
    private final BufferedReader reader;
    private Menu menu;
    private final LaunchMenu launchMenu;
    private final MainMenu mainMenu;
    private final OperationsMenu operationsMenu;
    private final FindInfoMenu findInfoMenu;

    public ArrayList<Apartment> getApartments() {return apartments;}
    public BufferedReader getReader() {return reader;}

    public Operator() {
        this.operator = this;
        reader = new BufferedReader(new InputStreamReader(System.in));
        launchMenu = new LaunchMenu();
        mainMenu = new MainMenu();
        operationsMenu = new OperationsMenu();
        findInfoMenu = new FindInfoMenu();
        menu = launchMenu;
    }



    public int readInt(){
        int result = 0;
        while(true) {
            try {
                result = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Something wrong, try again.");
                menu.print();
            }
        }
        return result;
    }
    public int readRegularInt(){
        int result = 0;
        while(true) {
            try {
                result = Integer.parseInt(reader.readLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer.");
            }
        }
        return result;
    }
    private double readDouble(){
        double result = 0;
        while (true) {
            try {
                result = Double.parseDouble(readLineAgain());
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer or fractional number.");
            }
        }
        return result;
    }
    public String readLineAgain(){
        String result = "nope";
        try {
            result = reader.readLine();
        }
        catch (Exception e) {
            readLineAgain();
        }
        return result;
    }


    /**main methods**/
    public void launch(){
        nextStep();
    }
    public void nextStep(){
        menu.print();
        menu.choice(operator);
    }
    /**main menu methods**/
    public void exit(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createNew() {
        apartments = new ArrayList<>();
        menu = operationsMenu;
        nextStep();
    }
    public void load(){
        if (new File("ApartmentsList.txt").length() > 0){
            try(ObjectInputStream isStream = new ObjectInputStream(new FileInputStream("ApartmentsList.txt"))){
                apartments = (ArrayList<Apartment>) isStream.readObject();
                menu = operationsMenu;
                System.out.println("Loaded");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Nothing to Load!");
            menu = launchMenu;
        }
        nextStep();
    }
    /**main menu methods**/
    public void toOperationsMenu() {
        menu = operationsMenu;
        nextStep();
    }
    public void toMainMenu() {
        menu = mainMenu;
        nextStep();
    }

    /**Operations menu methods**/
    public void save(){
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("ApartmentsList.txt"))){
            if (apartments.size() != 0){
                outStream.writeObject(apartments);
                System.out.println("Saved.");
            }
            else {
                System.out.println("Nothing to save!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu = operationsMenu;
        nextStep();
    }

    public void showList(){
        apartments.forEach(Apartment::print);
        nextStep();
    }

    public void addNewApartment(){
        Apartment tempApartment = new Apartment().createApartment(operator);
        if(tempApartment.getStreetName() != null) {
            long i = apartments.stream()
                    .filter(x -> x.getApartmentNumber() == tempApartment.getApartmentNumber())
                    .filter(x -> x.getApartmentSquare() == tempApartment.getApartmentSquare())
                    .filter(x -> x.getFloor() == tempApartment.getFloor())
                    .filter(x -> x.getRoomsCount() == tempApartment.getRoomsCount())
                    .filter(x -> x.getStreetName().equals(tempApartment.getStreetName()))
                    .count();
            if (i != 0) System.out.println("This apatment is already exist");
            else {
                apartments.add(tempApartment);
                System.out.println("Added");
            }
        }
        nextStep();
    }
    public void removeApartment(int id){
        if (apartments.stream().anyMatch(x -> x.getId()==id)){
            apartments.remove(apartments.stream().filter(x -> x.getId()==id).findAny().get());
            System.out.println("Removed.");
        }
        else System.out.println("There is no such id!");
        nextStep();
    }
    public void findInfo(){
        menu = findInfoMenu;
        nextStep();
    }

    /**Find Info menu methods**/


    public void backToOperationsMenu(){
        menu = operationsMenu;
        nextStep();
    }
    public void findApartmentsByRoomsCount(){
        System.out.println("Enter rooms count");
        int count;
        while ((count = readRegularInt()) <= 0) {
            System.out.println("Enter rooms count more than 0.");
        }
        int finalCount = count;
        apartments.stream().filter(x -> x.getRoomsCount() == finalCount).forEach(Apartment::print);
        nextStep();
    }
    public void findApartmentsByRoomsCountAndFloor(){
        int count;
        int minFloor;
        int maxFloor;
        System.out.println("Enter rooms count");
        while ((count = readRegularInt()) <= 0) {
            System.out.println("Enter rooms count more than 0.");
        }
        System.out.println("Enter min floor");
        while ((minFloor = readRegularInt()) <= 0) {
            System.out.println("Enter min floor more than 0.");
        }
        System.out.println("Enter max floor");
        while ((maxFloor = readRegularInt()) <= 0 || maxFloor < minFloor) {
            System.out.println("Enter max floor more than min floor.");
        }

        int finalCount = count;
        int finalMinFloor = minFloor;
        int finalMaxFloor = maxFloor;
        apartments.stream()
                .filter(x -> x.getRoomsCount() == finalCount)
                .filter(x -> x.getFloor() >= finalMinFloor)
                .filter(x -> x.getFloor() <= finalMaxFloor)
                .forEach(Apartment::print);
        nextStep();
    }
    public void sortApartmentsBySquare(){
        ArrayList<Apartment> apartmentsTemp = new ArrayList<>(apartments);
        apartmentsTemp.sort(Comparator.comparing(Apartment::getApartmentSquare));
        apartmentsTemp.forEach(Apartment::print);
        nextStep();
    }
    public void findApartmentsBySquare(){
        System.out.println("Enter square");
        double square;
        while ((square = readDouble()) <= 0) {
            System.out.println("Enter square more than 0.");
        }
        double finalSquare = square;

        ArrayList<Apartment> apartmentsTemp = new ArrayList<>(apartments);
        apartmentsTemp.sort(Comparator.comparing(Apartment::getApartmentSquare).thenComparing(Apartment::getFloor).reversed());
        apartmentsTemp.stream().filter(x -> x.getApartmentSquare() >= finalSquare).forEach(Apartment::print);
        nextStep();
    }

    public void sortApartmentsByFloors() {
        ArrayList<Apartment> apartmentsTemp = new ArrayList<>(apartments);
        apartmentsTemp.sort(Comparator.comparing(Apartment::getFloor).reversed());
        apartmentsTemp.forEach(Apartment::print);
        nextStep();
    }
    public void showApartmentsOnFloor() {
        System.out.println("Enter floor");
        int floor;
        while ((floor = readRegularInt()) <= 0) {
            System.out.println("Enter floor more than 0.");
        }
        int finalFloor = floor;
        apartments.stream().filter(x -> x.getFloor() == finalFloor).forEach(Apartment::print);
        nextStep();
    }

}
