package Lesson_2.Home;

public class Assistant implements Worker{
    private int money = 100;

    @Override
    public String doWork() {
        money +=10;
        return "чинит компьютер";
    }

    @Override
    public String goHome() {
        money -= 1;
        return "отправляется в съемную квартиру";
    }

    @Override
    public String goEat() {
        money -= 8;
        return "идет в кафе";
    }

    @Override
    public int howManyMoneyHave() {
        return money;
    }
}

