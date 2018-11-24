package ma.sqli.tests.cloudinfrastructure;

public class MachineStateStopped implements MachineState {
    @Override
    public void goInactive(CloudMachine cloudMachine) {
        
    }

    @Override
    public void goRunning(CloudMachine cloudMachine) {

    }

    @Override
    public void goStopped(CloudMachine cloudMachine) {
    }

    @Override
    public String print(){
        return "stopped";
    }
}
