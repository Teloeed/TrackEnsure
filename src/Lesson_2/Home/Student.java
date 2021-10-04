package Lesson_2.Home;

public class Student implements Worker {
    private int money = 20;

    @Override
    public String doWork() {
       return "идет на лекцию";
    }

    @Override
    public String goHome() {
        return "отправляется в общежитие";
    }

    @Override
    public String goEat() {
        money -= 1;
        return "идет в столовую";
    }

    @Override
    public int howManyMoneyHave() {
        return money;
    }
}
