package elections.system;

import elections.system.menu.*;
import elections.system.partys.Party;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ElectionCommission implements Serializable{
    private Map<Date, Archive> elections;
    private BufferedReader reader;
    private Worker worker;
    private Archive currentArchive;
    private Menu menu;
    private MainMenu mainMenu;
    private LaunchMenu launchMenu;
    private OperationsMenu operationsMenu;
    private EditFunctionsMenu editFunctionsMenu;
    private AutomaticFunctionsMenu automaticFunctionsMenu;

    public ElectionCommission() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.worker = new Worker();
        this.mainMenu = new MainMenu();
        this.launchMenu = new LaunchMenu();
        this.operationsMenu = new OperationsMenu();
        this.editFunctionsMenu = new EditFunctionsMenu();
        this.automaticFunctionsMenu = new AutomaticFunctionsMenu();
        this.menu = launchMenu;
    }
    public String readName(){return worker.readName(reader);}
    public int readInt() {return worker.readInt(reader);}
    public String readINN() {return worker.readINN(reader);}
    public int readPassport() {return worker.readPassport(reader);}
    public Date readBirthday() {return worker.readBirthday(reader);}
    public int readBoolean() {return worker.readBoolean(reader);}
    public Archive getArchive() {return currentArchive;}
    public String readString() {return worker.readString(reader);}




    /**main methods**/
    public void launch(){
        nextStep();
    }
    public void toOperationsMenu() {menu = operationsMenu; nextStep();}
    public void toMainMenu() {menu = mainMenu; nextStep();}
    public void nextStep(){menu.print(); menu.choice(this);}

    /**launch menu methods**/
    public void exit() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void load(){
        if (new File("Elections.txt").length() > 0){
            try(ObjectInputStream isStream = new ObjectInputStream(new FileInputStream("Elections.txt"))){
                elections = (HashMap<Date, Archive>) isStream.readObject();
                currentArchive = (Archive) isStream.readObject();
                menu = mainMenu;
                System.out.println("Loaded");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Nothing to Load!");
            menu = launchMenu;
        }
        nextStep();
    }
    public void startNewFile() {
        elections = new HashMap<>();
        currentArchive = new Archive();
        elections.put(new Date(), currentArchive);
        toOperationsMenu();
    }


    /**main menu methods**/
    public void save() {
        try(ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("Elections.txt"))){
            if (elections.size() != 0){
                outStream.writeObject(elections);
                outStream.writeObject(currentArchive);
                System.out.println("Saved.");
            }
            else {
                System.out.println("Nothing to save!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        toMainMenu();
    }
    public void startNewElections() {
        currentArchive = new Archive();
        elections.put(new Date(), currentArchive);
        toOperationsMenu();
    }

    /**Operations Menu methods**/
    public void toAutomaticFunctionsMenu() {menu = automaticFunctionsMenu; nextStep();}
    public void toEditFunctionsMenu() {menu = editFunctionsMenu; nextStep();}

    /**Automatic Menu methods**/
    public void addRandomVoters() { currentArchive.addRandomVoters(this); nextStep(); }
    public void createBaseVotingStations() { System.out.println(currentArchive.createBaseVotingStations()); nextStep(); }
    public void autoLinkingVotersToStations() { currentArchive.autoLinkingVotersToStations(); nextStep(); }
    public void addRandomParties() { currentArchive.addRandomParties(this); nextStep(); }
    public void autoLinkingVotersToParties() { currentArchive.autoLinkingVotersToParties(this); nextStep(); }

    /**Edit Functions Menu methods**/
    public void addVoter(){ currentArchive.addVoter(this); nextStep(); }
    public void addPartyVoter() { currentArchive.addPartyVoter(this); nextStep(); }
    public void createNewVotingStation() { currentArchive.createNewVotingStation(this); nextStep(); }
    public void addParty(){ currentArchive.addParty(this); nextStep(); }
    public void changeVotingStationForVoter() { currentArchive.changeVotingStationForVoter(this); nextStep(); }

    /**next Menu methods**/
    public void showVoters(){ currentArchive.showVoters(); nextStep(); }
    public void showVotingStations() { currentArchive.showVotingStations(); nextStep(); }
    public void showParties() { currentArchive.showParties(); nextStep(); }
    public void showPrimaries() { currentArchive.showPrimaries(this); nextStep(); }
    public void simulateElections() {System.out.println(currentArchive.simulateElections()); nextStep(); }
}
