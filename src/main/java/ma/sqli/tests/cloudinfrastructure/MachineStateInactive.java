package ma.sqli.tests.cloudinfrastructure;

public class MachineStateInactive implements MachineState {
    @Override
    public void goInactive(CloudMachine cloudMachine) {

    }

    @Override
    public void goRunning(CloudMachine cloudMachine) {
        cloudMachine.setState(new MachineStateRunning());
    }

    @Override
    public void goStopped(CloudMachine cloudMachine) {
    }

    @Override
    public String print(){
        return "inactive";
    }
}
