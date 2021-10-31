package elections;

import elections.system.ElectionCommission;



public class Launcher {

    public static void main(String[] args){
        ElectionCommission electionCommission = new ElectionCommission();
        electionCommission.launch();
    }
}
