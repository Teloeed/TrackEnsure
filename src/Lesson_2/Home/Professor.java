package Lesson_2.Home;

public class Professor implements Worker {
    private int money = 999;

    @Override
    public String doWork() {
        money += 50;
        return "ведет лекцию";
    }

    @Override
    public String goHome() {
        money -= 25;
        return "отправляется в свой частный дом";
    }

    @Override
    public String goEat() {
        money -= 70;
        return "идет в ресторан";
    }

    @Override
    public int howManyMoneyHave() {
        return 999;
    }
}

