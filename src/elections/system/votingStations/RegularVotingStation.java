package elections.system.votingStations;

import elections.system.voters.Voter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegularVotingStation implements VotingStation, Serializable {
    private int number;
    private String address;

    public RegularVotingStation(int number, String address, List<Voter> voters) {
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
