package Lesson_2.Home;

public class University {
    private static University university;
    private final Target[] list;

    private University() {list = fillList();}

    public static University getUniversity(){
        if (university==null){
            university = new University();
        }
        return university;
    }

    public void check(){
        doActions(list);
    }
    private Target[] fillList(){
        Target[] list = {
                new Target("Ivanich" , new Professor()),
                new Target("Petrovich" , new Professor()),
                new Target("Dimon" , new Student()),
                new Target("Katya" , new Student()),
                new Target("Viktor" , new Professor()),
                new Target("Pasha" , new Student()),
                new Target("Galina" , new Assistant()),
                new Target("Dorian" , new Assistant()),
                new Target("Glasha" , new Assistant()),
                new Target("Igor" , new Student())
        };
        return list;
    }
    private int randomPerson(){
        return (int)((Math.random()*10 + Math.random()*10)/2);
    }
    private String doRandomAction(Target target){
        int rand = (int)(Math.random()*4);
        String line = switch (rand) {
            case 0 -> target.doWork();
            case 1 -> target.goHome();
            case 2 -> target.goEat();
            case 3 -> target.howManyMoneyHave();
            default -> null;
        };
        return line;
    }
    private void doActions(Target[] list){
        for(int i = 0; i < 10; i++){
            print(doRandomAction(list[randomPerson()]));
        }
    }
    private void print(String line){
        System.out.println(line);
    }
}
