package ma.sqli.tests.cloudinfrastructure;

public class MachineStateStopped implements MachineState {
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
        return "stopped";
    }
}
