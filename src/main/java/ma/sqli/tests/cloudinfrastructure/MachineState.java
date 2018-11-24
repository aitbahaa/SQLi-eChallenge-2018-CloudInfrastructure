package ma.sqli.tests.cloudinfrastructure;

interface MachineState {
     void goInactive();
     void goRunning();
     void goStopped();
     String print();
}
