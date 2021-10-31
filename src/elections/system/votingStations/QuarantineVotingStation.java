package elections.system.votingStations;

import elections.system.voters.Voter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuarantineVotingStation implements VotingStation, Serializable {
    private final int number;
    private final String address;

    public QuarantineVotingStation(int number, String address, List<Voter> voters) {
        this.number = number;
        this.address = address;
    }
    public int getNumber() {return number;}
    public String getAddress() {return address;}

    public void print(){
        System.out.println("["
                + "Voting Station type: " + this.getClass().getSimpleName() + "; "
                + "№" + number + "; "
                + "Address: " + address + "]");
    }
}
