package Lesson_3.Home1;

public class Main {
       public static void main(String... args) {
           Flower flower1 = new Flower("Роза", 20);
           Flower flower2 = new Flower("Гортензия", 50);
           Flower flower3 = new Flower("Тюльпан", 5);
           Flower flower4 = new Flower("Пион", 8);
           Flower flower5 = new Flower("Ромашка", 2);

           Bouquet bouquet1 = new Bouquet();
           Bouquet bouquet2 = new Bouquet();

           bouquet1.add(flower1, flower2);
           bouquet2.add(flower3, flower4, flower5);

           bouquet1.show();
           bouquet1.checkFreshness();
           bouquet2.show();
           bouquet2.checkFreshness();
           bouquet1.findFlowerByStem(15,20);
        }
    }

