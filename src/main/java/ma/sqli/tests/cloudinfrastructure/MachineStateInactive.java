package ma.sqli.tests.cloudinfrastructure;

public class MachineStateInactive implements MachineState {
    @Override
    public void goInactive() {

    }

    @Override
    public void goRunning() {

    }

    @Override
    public void goStopped() {

    }

    @Override
    public String print(){
        return "inactive";
    }
}
