package elections.system.votingStations;

import elections.system.voters.Voter;

import java.io.Serializable;
import java.util.List;

public interface VotingStation {
    int getNumber();
    String getAddress();
    void print();
}
