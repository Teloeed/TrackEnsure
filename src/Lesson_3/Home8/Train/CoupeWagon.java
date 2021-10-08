package Lesson_3.Home8.Train;

public class CoupeWagon implements Wagon{
    private final int passengers = 17 + (int)(Math.random()*10);
    private final int bagsForPassengers = 3;
    private final double price = 421.53 + (int)(Math.random()*35);
    private final int comfortClass = 2;
    private final int number;

    public CoupeWagon(int number) {
        this.number = number;
    }

    @Override
    public int getPassengers() {
        return passengers;
    }

    @Override
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
