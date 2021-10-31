package elections.system;

import Lesson_5.Home.System.Apartment;
import elections.system.partys.Party;
import elections.system.voters.Voter;
import elections.system.votingStations.QuarantineVotingStation;
import elections.system.votingStations.RegularVotingStation;
import elections.system.votingStations.SpecialVotingStation;
import elections.system.votingStations.VotingStation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Archive  implements Serializable {
    private List<Voter> voters;
    private List<Party> parties;
    private List<VotingStation> votingStations;

    public List<Party> getParties() {return parties;}
    public List<Voter> getVoters() {return voters;}
    public List<VotingStation> getVotingStations() {return votingStations;}

    public Archive() {
        this.voters = new ArrayList<>();
        this.parties = new ArrayList<>();
        this.votingStations = new ArrayList<>();
    }

    public void addVoter(ElectionCommission electionCommission){ Voter.VoterAdder.add(electionCommission); System.out.println("Added"); }
    public void addParty(ElectionCommission electionCommission){ Party.PartyAdder.add(electionCommission); System.out.println("Added"); }

    public void addRandomVoters(ElectionCommission electionCommission) {
        int count = 0;
        while (true) {
            System.out.println("Enter voters count number (more than 0)");
            count = electionCommission.readInt();
            if (count > 0) break;
        }
        Voter.RandomVoter.addRandomVoters(electionCommission, count);
        System.out.println("Added");
    }
    public String createBaseVotingStations(){
        if (votingStations.size() > 0) return "You can't Generate base Voting Stations, because list isn't empty";
        List<String> address = new ArrayList<>(List.of(
                "Nikolskaya ","Jukova ","Lenina ","Navarinskaya ","Sadovaya ",
                "Lazurnaya ","Ozernaya ","Sovetskaya ","Dekabristov ","Centralnaya ",
                "Electronnaya ","KarlaLibnihta ","Vodoprovodnaya ","Buznika ","Slobodskaya ",
                "Artema ","Kirova ","Kosmonavtov ","Fedorova ","Poperechnaya "));

        votingStations.add(new SpecialVotingStation(1, address.get((int) (Math.random()*address.size()))+(int)(Math.random()*200), new ArrayList<>()));
        votingStations.add(new QuarantineVotingStation(2, address.get((int) (Math.random()*address.size()))+(int)(Math.random()*200), new ArrayList<>()));
        votingStations.add(new RegularVotingStation(3, address.get((int) (Math.random()*address.size()))+(int)(Math.random()*200), new ArrayList<>()));
        votingStations.add(new RegularVotingStation(4, address.get((int) (Math.random()*address.size()))+(int)(Math.random()*200), new ArrayList<>()));
        votingStations.add(new RegularVotingStation(5, address.get((int) (Math.random()*address.size()))+(int)(Math.random()*200), new ArrayList<>()));
        votingStations.add(new RegularVotingStation(6, address.get((int) (Math.random()*address.size()))+(int)(Math.random()*200), new ArrayList<>()));
        votingStations.add(new RegularVotingStation(7, address.get((int) (Math.random()*address.size()))+(int)(Math.random()*200), new ArrayList<>()));
        return "Created";
    }
    public void createNewVotingStation(ElectionCommission electionCommission){
        int type = -1;
        while(true){
            System.out.println("Enter Voting Stations type number:" + "\n"
                    + "1: QuarantineVotingStation,"
                    + "\n" + "2: SpecialVotingStation,"
                    + "\n" + "3: RegularVotingStation");
            type = electionCommission.readInt();
            if (type > 0 && type < 4) break;
        }
        System.out.println("Enter voting station address");
        String address = electionCommission.readString() + " "+ (int)(Math.random()*200);
        int number = 1;
        if (votingStations.size() != 0) number = votingStations.stream().max(Comparator.comparing(VotingStation::getNumber)).get().getNumber() + 1;

        VotingStation votingStation = new QuarantineVotingStation(number, address, new ArrayList<>());
        switch(type){
            case (3) -> votingStation = new RegularVotingStation(number, address, new ArrayList<>());
            case (2) -> votingStation = new SpecialVotingStation(number, address, new ArrayList<>());
        }
        votingStations.add(votingStation);
        System.out.println("Added");
    }

    public void showVoters(){
        for(int i = 1; i <= voters.size(); i++){
            System.out.print(i + " ");
            voters.get(i - 1).print();
        }
    }

    public void autoLinkingVotersToStations() {
        List<VotingStation> quarantineVotingStations = votingStations.stream().filter(x -> x instanceof QuarantineVotingStation).collect(Collectors.toList());
        List<VotingStation> specialVotingStations = votingStations.stream().filter(x->x instanceof SpecialVotingStation).collect(Collectors.toList());
        List<VotingStation> regularVotingStations = votingStations.stream().filter(x->x instanceof RegularVotingStation).collect(Collectors.toList());
        if(quarantineVotingStations.size() == 0){System.out.println("No Quarantine Stations. Quarantine Voters wont be added to any stations.");}
        if(specialVotingStations.size() == 0){System.out.println("No Special Stations. Special Voters wont be added to any stations.");}
        if(regularVotingStations.size() == 0){System.out.println("No Regular Stations. Regular Voters wont be added to any stations.");}

        for (Voter voter : voters){
            if (voter.getVotingStation() == 0){
                if (voter.isOnQuarantine()){
                    if(quarantineVotingStations.size() > 0) {
                        VotingStation votingStation = quarantineVotingStations.get((int)(Math.random() * quarantineVotingStations.size()));
                        voter.setVotingStation(votingStation.getNumber());
                    }
                }
                else if (voter.isSpecialService()){
                    if(specialVotingStations.size() > 0) {
                        VotingStation votingStation = specialVotingStations.get((int) (Math.random() * specialVotingStations.size()));
                        voter.setVotingStation(votingStation.getNumber());
                    }
                }
                else {
                    if(regularVotingStations.size() > 0) {
                        VotingStation votingStation = regularVotingStations.get((int) (Math.random() * regularVotingStations.size()));
                        voter.setVotingStation(votingStation.getNumber());
                    }
                }
            }
        }
        System.out.println("Done");
    }
    public void changeVotingStationForVoter(ElectionCommission electionCommission){
        System.out.println("Choose voter from list(enter number)");
        int voterNumber = 0;
        int votingStationNumber = 0;
        try {
            if(voters.size() == 0) throw new ArithmeticException();
            if(votingStations.size() == 0) throw new IOException();
            while (true) {
                showVoters();
                System.out.println("Choose voter from list(enter number)");
                voterNumber = electionCommission.readInt();
                if (voterNumber > 0 && voterNumber <= voters.size()) break;
            }
            while (true) {
                showVotingStations();
                System.out.println("Choose voter from list(enter number)");
                votingStationNumber = electionCommission.readInt();
                if (votingStationNumber > 0 && votingStationNumber <= votingStations.size()) {
                    VotingStation votingStation = votingStations.get(votingStationNumber-1);
                    Voter voter = voters.get(voterNumber-1);
                    if (votingStation instanceof SpecialVotingStation && voter.isSpecialService()) break;
                    else if (votingStation instanceof QuarantineVotingStation && voter.isOnQuarantine()) break;
                    else if (!voter.isSpecialService() && !voter.isOnQuarantine() && votingStation instanceof RegularVotingStation) break;
                    else System.out.println("This station is not suitable for this voter!");
                }
            }
            voters.get(voterNumber-1).setVotingStation(votingStations.get(votingStationNumber-1).getNumber());
        }
        catch (ArithmeticException e) {System.out.println("No voters to add.");}
        catch (IOException e) {System.out.println("No stations to add.");}
        System.out.println("Done");
    }

    public void showVotingStations() {
        for(int i = 1; i <= votingStations.size(); i++){
            System.out.print(i + " ");
            votingStations.get(i - 1).print();
        }
    }

    public void addPartyVoter(ElectionCommission electionCommission) {
        int partyNumber = 0;
        int voterNumber = 0;
        if (parties.size() != 0) {
            while (true) {
                showParties();
                System.out.println("Choose party from list(enter number)");
                partyNumber = electionCommission.readInt();
                if (partyNumber > 0 && partyNumber <= parties.size()) break;
            }
            if (voters.size() != 0) {
                while (true) {
                    showVoters();
                    System.out.println("Choose voter from list(enter number)");
                    voterNumber = electionCommission.readInt();
                    if (voterNumber > 0 && voterNumber <= voters.size()) {
                        Party party = parties.get(partyNumber-1);
                        Voter voter = voters.get(voterNumber-1);
                        if(voter.getParty() == null) {
                            voter.setParty(party);
                            break;
                        } else System.out.println("This voter is already in some party");
                    }
                }
            } else System.out.println("No voters to add!");
        }  else System.out.println("No parties!");
    }

    public void showParties() {
        for(int i = 1; i <= parties.size(); i++){
            System.out.print(i + " ");
            parties.get(i - 1).print();
        }
    }

    public void addRandomParties(ElectionCommission electionCommission) {
        int count = 0;
        while (true) {
            System.out.println("Enter parties count number (more than 0)");
            count = electionCommission.readInt();
            if (count > 0) break;
        }
        Party.RandomParty.addRandomParties(electionCommission, count);
    }
    public void autoLinkingVotersToParties(ElectionCommission electionCommission) {
        List<Voter> votersNoParty = voters.stream().filter(x->x.getParty()==null).collect(Collectors.toList());
        int count = 0;
        try {
            if(votersNoParty.size() == 0) throw new ArithmeticException();
            if(parties.size() == 0) throw new IOException();
            while (true) {
                System.out.println("Enter voters count number (more than 0 and low than " + (votersNoParty.size()+1) + ")");
                count = electionCommission.readInt();
                if (count > 0 && count <= votersNoParty.size()) {
                    for (int i = 0; i < count; i++) {
                        Party party = parties.get((int) (Math.random() * parties.size()));
                        voters.get(i).setParty(party);
                        party.getPrimaries().put(voters.get(i), (int) (Math.random() * 200));
                    }
                    break;
                }
            }
        }
        catch (ArithmeticException e) {System.out.println("No voters to add.");}
        catch (IOException e) {System.out.println("No parties to add.");}
        System.out.println("Done");
    }

    public void showPrimaries(ElectionCommission electionCommission) {
        int number = 0;
        while(true){
            showParties();
            System.out.println("Choose and enter party number from list");
            number = electionCommission.readInt();
            if(number > 0 && number <= parties.size()) break;
        }
        Party party = parties.get(number-1);
        party.getPrimaries().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(x-> x.getKey().primariesPrint());
    }

    public void askVotersAboutVoting() {
        Date electionsDate = new Date();
        voters.forEach(x -> x.surveyVote(electionsDate));
    }
    public String simulateElections(){
        askVotersAboutVoting();
        List<Voter> votersForElection = voters.stream().filter(Voter::isWantToVote).collect(Collectors.toList());
        autoLinkingVotersToStations();
        Map<Party, Integer> result = new HashMap<>();
        for(Party party : parties){
            result.put(party, 0);
        }
        int partyNumbers = parties.size();
        for(Voter voter : votersForElection){
            int randomParty = (int)(Math.random()*partyNumbers);
            Party party = parties.get(randomParty);
            int tempResult = result.get(party) + 1;
            result.put(party, tempResult);
        }
        result.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        double percent = votersForElection.size()*1.0 / 100;
        DecimalFormat df=new DecimalFormat("0.00");
        for(Iterator<Map.Entry<Party, Integer>> it = result.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).iterator(); it.hasNext();){
            Map.Entry<Party, Integer> pair = it.next();
            String partyName = pair.getKey().getName();
            int votes = pair.getValue();
            double percentages = 0;
            if (votes != 0) percentages = votes / percent;
            System.out.println("Party: " + partyName + "; Votes: " + votes + "; Percentages - " + df.format(percentages) + "%");
        }

        return "Done";
    }
}
