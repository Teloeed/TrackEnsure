package elections.system.menu;


import elections.system.ElectionCommission;

public class MainMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Exit" + "\n" +
                "1. Start new elections" + "\n" +
                "2. Continue elections" + "\n" +
                "3. Save" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(ElectionCommission electionCommission){
        int number = -1;
        while(true) {
            number = electionCommission.readInt();
            if (number < 0 || number > 3) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> electionCommission.exit();
            case 1 -> electionCommission.startNewElections();
            case 2 -> electionCommission.toOperationsMenu();
            case 3 -> electionCommission.save();
        }
    }
}
