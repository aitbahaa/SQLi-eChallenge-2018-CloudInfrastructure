package ma.sqli.tests.cloudinfrastructure;

interface MachineState {
     void goInactive(CloudMachine cloudMachine);
     void goRunning(CloudMachine cloudMachine);
     void goStopped(CloudMachine cloudMachine);
    String print();
}
