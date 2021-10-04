package Lesson_2.Home;

public class Target {
       private final String name;
       private Worker workerType;

        public Target(String name, Worker worker) {
                this.name = name;
                this.workerType = worker;
        }

        public String getName() {return name;}
        public Worker getWorker() {return workerType;}
        public void setWorker(Worker worker) {this.workerType = worker;}

        public String doWork() {
            return workerType.getClass().getSimpleName() + " " + name + " " + workerType.doWork();
        }

        public String goHome() {
            return workerType.getClass().getSimpleName() + " " + name + " " + workerType.goHome();
        }

        public String goEat() {
            return workerType.getClass().getSimpleName() + " " + name + " " + workerType.goEat();
        }

        public String howManyMoneyHave() {
            return workerType.getClass().getSimpleName() + " " + name + " have " + workerType.howManyMoneyHave() + "$";
        }
}
