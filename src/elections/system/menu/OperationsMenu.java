package elections.system.menu;


import elections.system.ElectionCommission;

public class OperationsMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Automatic Functions Menu" + "\n" +
                "2. Edit Functions Menu" + "\n" +
                "3. Show Voters list" + "\n" +
                "4. Show Voting Stations list" + "\n" +
                "5. Show Parties list" + "\n" +
                "6. Show Party Primaries" + "\n" +
                "7. Simulate elections" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(ElectionCommission electionCommission){
        int number = -1;
        while(true) {
            number = electionCommission.readInt();
            if (number < 0 || number > 7) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> electionCommission.toMainMenu();
            case 1 -> electionCommission.toAutomaticFunctionsMenu();
            case 2 -> electionCommission.toEditFunctionsMenu();
            case 3 -> electionCommission.showVoters();
            case 4 -> electionCommission.showVotingStations();
            case 5 -> electionCommission.showParties();
            case 6 -> electionCommission.showPrimaries();
            case 7 -> electionCommission.simulateElections();
        }
    }

}