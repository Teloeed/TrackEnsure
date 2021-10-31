package elections.system.voters;

import elections.system.ElectionCommission;
import elections.system.partys.Party;
import elections.system.votingStations.QuarantineVotingStation;
import elections.system.votingStations.VotingStation;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Voter  implements Serializable {
    private final String firstName;
    private final String lastName;
    private final int passport;
    private final String identificationNumber;
    private final Date birthday;
    private int votingStation;
    private boolean isSpecialService;
    private boolean onQuarantine;

    private int partyRank = 0;
    private Party party = null;
    private boolean wantToVote = false;
    private SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    private Voter(String firstName, String lastName, int passport, String identificationNumber, Date birthday, boolean isSpecialService, boolean onQuarantine) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.identificationNumber = identificationNumber;
        this.birthday = birthday;
        this.isSpecialService = isSpecialService;
        this.onQuarantine = onQuarantine;
        this.votingStation = 0;
    }

    public void surveyVote(Date electionsDate) {
        if((electionsDate.getTime() - birthday.getTime())/1000 > 56813352) {//число миллисекунд умноженное на 1000, которое пройдет с рождения до 18тилетия
            wantToVote = (int) (Math.random() * 10) <= 5;
        }
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}

    public int getPartyRank() {return partyRank;}
    public void setPartyRank(int partyRank) {this.partyRank = partyRank;}
    public void setParty(Party party) {this.party = party;}
    public int getVotingStation() {return votingStation;}
    public void setVotingStation(int votingStation) {this.votingStation = votingStation;}
    public boolean isSpecialService() {return isSpecialService;}
    public boolean isOnQuarantine() {return onQuarantine;}
    public boolean isWantToVote() {return wantToVote;}

    public Party getParty() {return party;}
    public void print(){
        System.out.println("["
                + firstName + " "
                + lastName + "; "
                + "Passport №" + passport + "; "
                + "INN: " + identificationNumber + "; "
                + "Birthday: " + output.format(birthday) + "; "
                + (isSpecialService ? "In Special Service; " : "Not in Special Service; ")
                + (onQuarantine ? "On Quarantine" : "Not on Quarantine")
                + "; Voting station: " + (votingStation==0 ? "not linked" : votingStation)
                + "; Party: " + (party==null ? "not in party" : party.getName()) + "]");
    }
    public void primariesPrint(){
        System.out.println("["
                + firstName + " "
                + lastName + "; "
                + "Birthday: " + output.format(birthday) + "; "
                +  "Rank in party: " + party.getPrimaries().get(this) + "]");
    }


    public static class VoterAdder {

        public static void add(ElectionCommission electionCommission) {
            System.out.println("Please enter First name");
            String firstName = electionCommission.readName();

            System.out.println("Please enter Last name");
            String lastName = electionCommission.readName();

            System.out.println("Please enter passport number");
            int passport = electionCommission.readPassport();

            System.out.println("Please enter identification number(10 numbers)");
            String identificationNumber = electionCommission.readINN();

            System.out.println("Please enter birthday in format: 01/01/1990");
            Date birthday = electionCommission.readBirthday();

            System.out.println("Is this voter a Special Service? Enter 1 if \"Yes\" or 0 if \"No\"");
            boolean isSpecialService = electionCommission.readBoolean() == 1;

            System.out.println("Is this voter in Quarantine? Enter 1 if \"Yes\" or 0 if \"No\"");
            boolean onQuarantine = electionCommission.readBoolean() == 1;

            Voter voter = new Voter(firstName, lastName, passport, identificationNumber, birthday, isSpecialService, onQuarantine);
            electionCommission.getArchive().getVoters().add(voter);
        }
    }

    public static class RandomVoter {
        private static List<String> firstNames = new ArrayList<>(List.of(
                "Alex", "Dana", "Kolya", "Dmitro", "Fedor", "Givi", "Alex",
                "Dominik", "Alla", "Fiona", "Katya", "Glasha", "Mara", "Oleg",
                "Maksim", "Igor", "Alexey", "Aslan", "Gorislav"));
        private static List<String> lastNames = new ArrayList<>(List.of(
                "Petrov", "Sidorov", "Alvin", "Forestow", "Field", "Brown", "Mopsov",
                "Green", "Double", "Korova", "Dich", "Gorliza", "Dabert", "Aprova",
                "Rekov", "Peshev", "Dostalo", "Pridumav", "Pushkin"));

        private static String getFirstName() {
            return (firstNames.get((int)(Math.random() * firstNames.size())));
        }
        private static String getLastName() {
            return (lastNames.get((int)(Math.random() * lastNames.size())));
        }

        private static int generatePassport() {
            int number1 = ((int) (Math.random() * 9))*1_000_000;
            int number2 = ((int) (Math.random() * 9))*1_000_00;
            int number3 = ((int) (Math.random() * 9))*1_000_0;
            int number4 = ((int) (Math.random() * 9))*1_000;
            int number5 = ((int) (Math.random() * 9))*1_00;
            int number6 = ((int) (Math.random() * 9))*1_0;
            int number7 = (int) (Math.random() * 9);
            return number1 + number2 + number3 + number4 + number5 + number6 + number7;
        }

        private static String generateINN() {
            String number0 = generatePassport()+"";
            int number8 = ((int) (Math.random() * 9))*100;
            int number9 = ((int) (Math.random() * 9))*10;
            int number10 = (int) (Math.random() * 9);
            return number0 + (number8 + number9 + number10);
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

        private static boolean isSpecial() {
            return (int) (Math.random() * 10) > 8;
        }

        private static boolean onQuarantine() {
            return (int) (Math.random() * 10) > 9;
        }

        public static void addRandomVoters(ElectionCommission electionCommission, int count) {
            for (int i = 0; i < count; i++) {
                Voter voter = new Voter(getFirstName(), getLastName(), generatePassport(), generateINN(), generateBirthday(), isSpecial(), onQuarantine());
                electionCommission.getArchive().getVoters().add(voter);
            }
        }
    }
}
