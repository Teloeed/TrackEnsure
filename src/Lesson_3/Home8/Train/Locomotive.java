package Lesson_3.Home8.Train;

public class Locomotive {
    private final int horsePower = 2000;

    public void print(){System.out.println("[" + this.getClass().getSimpleName() + ": " + horsePower + "HP" + "]");}
}
