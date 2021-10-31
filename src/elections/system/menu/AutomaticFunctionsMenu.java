package elections.system.menu;


import elections.system.ElectionCommission;

public class AutomaticFunctionsMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Add random Voters(count)" + "\n" +
                "2. Add random Parties(count)" + "\n" +
                "3. Generate base Voting Stations" + "\n" +
                "4. Automatic linking voters to Voting Stations" + "\n" +
                "5. Automatic linking voters to Parties(count)" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(ElectionCommission electionCommission){
        int number = -1;
        while(true) {
            number = electionCommission.readInt();
            if (number < 0 || number > 5) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> electionCommission.toOperationsMenu();
            case 1 -> electionCommission.addRandomVoters();
            case 2 -> electionCommission.addRandomParties();
            case 3 -> electionCommission.createBaseVotingStations();
            case 4 -> electionCommission.autoLinkingVotersToStations();
            case 5 -> electionCommission.autoLinkingVotersToParties();
        }
    }

}