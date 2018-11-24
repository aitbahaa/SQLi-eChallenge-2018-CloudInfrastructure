package ma.sqli.tests.cloudinfrastructure;

public class MachineStateRunning implements MachineState {
    @Override
    public void goInactive(CloudMachine cloudMachine) {

    }

    @Override
    public void goRunning(CloudMachine cloudMachine) {
        throw new MachineStateException();
    }

    @Override
    public void goStopped(CloudMachine cloudMachine) {
        cloudMachine.setState(new MachineStateStopped());
    }

    @Override
    public String print(){
        return "running";
    }
}
