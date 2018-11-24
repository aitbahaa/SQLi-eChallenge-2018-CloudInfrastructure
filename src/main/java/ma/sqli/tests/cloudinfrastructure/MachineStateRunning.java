package ma.sqli.tests.cloudinfrastructure;

public class MachineStateRunning implements MachineState {
    @Override
    public void goInactive() {
        
    }

    @Override
    public void goRunning() {
        throw new MachineStateException();
    }

    @Override
    public void goStopped() {

    }

    @Override
    public String print(){
        return "running";
    }
}
