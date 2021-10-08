package Lesson_3.Home8.Train;

public class RegularWagon implements Wagon{
    private final int passengers = 34 + (int)(Math.random()*20);
    private int bagsForPassengers = 1;
    private final double price = 152.65 + (int)(Math.random()*20);
    private final int comfortClass = 3;
    private final int number;

    public RegularWagon(int number) {
        this.number = number;
    }

    public int getPassengers() {
        return passengers;
    }

    public int getBaggage() {
        return bagsForPassengers*passengers;
    }

    public double getPrice() {
        return price;
    }

    public int getComfortClass() {
        return comfortClass;
    }

    public int getNumber() {return number;}

    public void print(){
        System.out.println("["
                + this.getClass().getSimpleName()
                + " №" + number + ": "
                + "Class - " + getComfortClass() + "; "
                + "Passengers - " + getPassengers() + "; "
                + "Baggage - " + getBaggage() + "; "
                + "Price - " + getPrice() + "грн.");
    }
}
