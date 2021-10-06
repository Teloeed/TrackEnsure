package Lesson_3.Home1;
    public class Flower implements Component{
        private final String name;
        private final int price;
        public final int freshness;
        private final int stemLength;

        public Flower(String name, int price) {
            this.name = name;
            this.price = price;
            this.freshness = (int)(Math.random()*5+1);
            this.stemLength = (int)(Math.random()*20+10);
        }

        public String getName() {
            return name;
        }

        @Override
        public void show() {

        }

        @Override
        public void showName() {
            System.out.println(name);
        }

        @Override
        public int getPrice() {
            return price;
        }

        public int getFreshness() {
            return freshness;
        }

        public int getStemLength() {
            return stemLength;
        }
    }

