package elections.system.partys;

import elections.system.ElectionCommission;
import elections.system.voters.Voter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Party  implements Serializable {
    private String name;
    private Faction faction;
    private Date birthday;
    private List<Voter> partyCandidates;
    private Map<Voter, Integer> primaries;
    private SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    public Party(String name, Faction faction, Date birthday, List<Voter> partyCandidates, Map<Voter, Integer> primaries) {
        this.name = name;
        this.faction = faction;
        this.birthday = birthday;
        this.partyCandidates = partyCandidates;
        this.primaries = primaries;
    }

    public String getName() {return name;}
    public Faction getFaction() {return faction;}
    public Date getBirthday() {return birthday;}
    public List<Voter> getPartyCandidates() {return partyCandidates;}
    public Map<Voter, Integer> getPrimaries() {return primaries;}

    public void print(){
        System.out.println("["
                + "Party - " + name
                + "; Faction: " + faction
                + "; Create date: " + output.format(birthday) + "]");
    }

    /**Add new Party**/
    public static class PartyAdder {
        public static void add(ElectionCommission electionCommission){
            System.out.println("Please enter Party name");
            String name = "";
            while (true){
                name = electionCommission.readName();
                String finalName = name;
                if(electionCommission.getArchive().getParties().stream().noneMatch(x->x.getName().toLowerCase(Locale.ROOT).equals(finalName.toLowerCase(Locale.ROOT)))) break;
                System.out.println("Such name in already exist");
            }

            System.out.println("Please choose faction: LEFT, RIGHT, CENTRIST");
            String factionName = electionCommission.readString();
            Faction faction;
            while (true){
                if (factionName.toUpperCase().equals(Faction.CENTRIST.name())) {
                    faction = Faction.CENTRIST;
                    break;
                }
                else if (factionName.toUpperCase().equals(Faction.RIGHT.name())){
                    faction = Faction.RIGHT;
                    break;
                }
                else if (factionName.toUpperCase().equals(Faction.LEFT.name())){
                    faction = Faction.LEFT;
                    break;
                }
                System.out.println("Please choose correct faction: LEFT, RIGHT, CENTRIST");
                factionName = electionCommission.readString();
            }

            System.out.println("Please enter party create date in format: : 01/01/1990");
            Date date = electionCommission.readBirthday();

            System.out.println("Please enter party leader");
            electionCommission.getArchive().showVoters();
            int number = 0;
            while(true){
                if ((number = electionCommission.readInt()) > 0) {
                    if (electionCommission.getArchive().getVoters().get(number-1).getParty() == null) break;
                    else System.out.println("This voter is already in some party");
                }
                else System.out.println("Please enter party leader number, more then 0");
            }
            List<Voter> partyCandidates = new ArrayList<>(List.of(electionCommission.getArchive().getVoters().get(number-1)));

            Map<Voter, Integer> primaries = new HashMap<>();
            primaries.put(partyCandidates.get(0), (int)(Math.random()*100));

            electionCommission.getArchive().getParties().add(new Party(name, faction, date, partyCandidates, primaries));
        }
    }

    /**Add Random Party**/
    public static class RandomParty {
        private static List<String> names = new ArrayList<>(List.of(
                "Zeleni", "Yabloko", "Komunisti", "Centristi", "Za narod", "Za zemlu", "Za hleb",
                "Demokrati", "Slavyane", "Nikolaevci", "Alkogoliki", "Strannie", "Pivmans", "Odinokie",
                "Rezvedenki", "Arbuzeri", "Mebelshiki", "Uborshiki", "Transvestiti"));

        private static String getName() {
            return (names.get((int)(Math.random() * names.size())));
        }

        private static Date generateBirthday() {
            int month = (int) (Math.random() * 12);
            int day = month == 2 ? (int) (Math.random() * 28) : (int) (Math.random() * 30);
            int year = 1920 + (int) (Math.random() * 82);
            String result = day + "/" + month + "/" + year;
            SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date date = new Date();
            try {
                date = input.parse(result);
            } catch (ParseException e) {
                System.out.println("Something wrong! Date is broken!");
            }
            return date;
        }
        private static Faction randomFaction() {
            Faction faction = Faction.LEFT;
            int rand = (int)(Math.random()*3);
            switch (rand){
                case (0) -> faction = Faction.RIGHT;
                case (1) -> faction = Faction.CENTRIST;
            }
            return faction;
        }

        public static void addRandomParties(ElectionCommission electionCommission, int count) {
            try {
                for (int i = 0; i < count; i++) {
                    String name = "";
                    int tryCount = 0;

                        while (true) {
                            name = getName();
                            String finalName = name;
                            if (electionCommission.getArchive().getParties().stream().noneMatch(x -> x.getName().toLowerCase(Locale.ROOT).equals(finalName.toLowerCase(Locale.ROOT))))
                                break;
                            else tryCount++;
                            if (tryCount == 1000) throw new Exception();
                        }
                        Party party = new Party(name, randomFaction(), generateBirthday(), new ArrayList<>(), new HashMap<Voter, Integer>());
                        Voter.RandomVoter.addRandomVoters(electionCommission, 1);
                        Voter leader = electionCommission.getArchive().getVoters().get(electionCommission.getArchive().getVoters().size()-1);
                        leader.setParty(party);
                        party.getPrimaries().put(leader, (int)(Math.random()*200));
                        electionCommission.getArchive().getParties().add(party);

                }
            } catch (Exception e) {System.out.println("All names for random parties are occupied. Some parties wasn't added.");}
        }
    }
}
