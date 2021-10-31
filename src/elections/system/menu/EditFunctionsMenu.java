package elections.system.menu;


import elections.system.ElectionCommission;

public class EditFunctionsMenu implements Menu {
    public void print() {
        System.out.println("__________________________________" + "\n" +
                "Menu:" + "\n" +
                "0. Back" + "\n" +
                "1. Add Voter" + "\n" +
                "2. Add party" + "\n" +
                "3. Add voter to party" + "\n" +
                "4. Add Voting Station" + "\n" +
                "5. Change Voting Station for voter" + "\n" +
                "Press number for change option" + "\n" +
                "__________________________________");
    }
    public void choice(ElectionCommission electionCommission){
        int number = -1;
        while(true) {
            number = electionCommission.readInt();
            if (number < 0 || number > 6) {
                System.out.println("Something wrong, try again.");
                print();
            }
            else break;
        }
        switch (number) {
            case 0 -> electionCommission.toOperationsMenu();
            case 1 -> electionCommission.addVoter();
            case 2 -> electionCommission.addParty();
            case 3 -> electionCommission.addPartyVoter();
            case 4 -> electionCommission.createNewVotingStation();
            case 5 -> electionCommission.changeVotingStationForVoter();
        }
    }

}