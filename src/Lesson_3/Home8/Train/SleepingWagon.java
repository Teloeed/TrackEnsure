package Lesson_3.Home8.Train;

public class SleepingWagon implements Wagon{
    private final int passengers = 10 + (int)(Math.random()*8);
    private final int bagsForPassengers = 5;
    private final double price = 867.26 + (int)(Math.random()*60);
    private final int comfortClass = 1;
    private final int number;

    public SleepingWagon(int number) {
        this.number = number;
    }

    @Override
    public int getPassengers() {
        return passengers;
    }

    public int getBaggage() {
        return bagsForPassengers*passengers;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
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
