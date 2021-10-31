package elections.system.menu;


import elections.system.ElectionCommission;

public class LaunchMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Exit" + "\n" +
                "1. Load" + "\n" +
                "2. Start new Elections File" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(ElectionCommission electionCommission){
        int number = -1;
        while(true) {
            number = electionCommission.readInt();
            if (number < 0 || number > 2) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> electionCommission.exit();
            case 1 -> electionCommission.load();
            case 2 -> electionCommission.startNewFile();
        }
    }
}
