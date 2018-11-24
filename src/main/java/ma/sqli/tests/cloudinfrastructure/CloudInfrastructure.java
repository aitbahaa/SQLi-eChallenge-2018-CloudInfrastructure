package ma.sqli.tests.cloudinfrastructure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CloudInfrastructure {
    /**
     * List of Stores
     */
    private List<CloudStore> stores = new LinkedList<>();

    /**
     * List of VirtualMachines
     */
    private List<CloudMachine> machines = new LinkedList<>();


    /**
     * Create a store from a StoreName
     *
     * @param storeName
     */
    public void createStore(String storeName) {
        if (storeExiste(storeName)) throw new CreateStoreException();
        this.stores.add(new CloudStore(storeName));
    }

    /**
     * Check if a store is already existed using the StoreName
     *
     * @param storeName
     * @return
     */
    private boolean storeExiste(String storeName) {
        CloudStore wantedStore = findStoreByStoreName(storeName);
        return wantedStore != null;
    }

    /**
     * Add documents to a CloudStore using StoreName
     *
     * @param storeName
     * @param documentsNames
     */
    public void uploadDocument(String storeName, String... documentsNames) {

        CloudStore wantedStore = findStoreByStoreName(storeName);

        if (wantedStore != null) {
            Arrays.stream(documentsNames)
                    .map(documentsName -> new Document(documentsName))
                    .forEach(document -> {
                        wantedStore.addDocument(document);
                    });
        }
    }

    /**
     * Find a store in the StoreList using StoreName
     *
     * @param storeName
     * @return
     */
    private CloudStore findStoreByStoreName(String storeName) {
        return this.stores
                .stream()
                .filter(store -> store.getName().equals(storeName))
                .findAny()
                .orElse(null);
    }

    /**
     * Return the description of the stores
     *
     * @return
     */
    public String listStores() {
        String storesDescription;

        storesDescription = stores.stream().map(CloudStore::printStore).collect(Collectors.joining("||"));

        return storesDescription;
    }

    /**
     * Delete a store by the StoreName
     *
     * @param storeName
     */
    public void deleteStore(String storeName) {
        CloudStore wantedStore = findStoreByStoreName(storeName);

        this.stores.remove(wantedStore);
    }

    /**
     * Clear all documents in a store by the StoreName
     *
     * @param storeName
     */
    public void emptyStore(String storeName) {
        CloudStore wantedStore = findStoreByStoreName(storeName);

        wantedStore.clearDocuments();
    }


    /**
     * Create a virtual machine with machine name, osm disk size and memory
     * @param machineName
     * @param operatingSystem
     * @param diskSize
     * @param memory
     */
    public void createMachine(String machineName, String operatingSystem, String diskSize, String memory) {
        this.machines.add(new CloudMachine(machineName,operatingSystem,diskSize,memory));
    }

    /**
     * Return the description of all the machines
     * @return
     */
    public String listMachines() {
        String machinesDescription;

        machinesDescription = machines.stream().map(CloudMachine::printMachine).collect(Collectors.joining("||"));

        return machinesDescription;
    }

    /**
     * Start a mchine using MachineName
     * @param machineName
     */
    public void  startMachine(String machineName) {
        CloudMachine wantedMachine = findMachineByMachineName(machineName);
        if(wantedMachine!=null)
            wantedMachine.goRunning();
    }

    /**
     * Find a virtual machine by MachineName
     * @param machineName
     * @return
     */
    private CloudMachine findMachineByMachineName(String machineName){
        CloudMachine wantedMachine = machines.stream()
                .filter(cloudMachine -> cloudMachine.getName().equals(machineName))
                .findAny()
                .orElse(null);
        return wantedMachine;
    }

    /**
     * Stop a mchine using MachineName
     * @param machineName
     */
    public void stopMachine(String machineName) {
        CloudMachine wantedMachine = findMachineByMachineName(machineName);
        if(wantedMachine!=null)
            wantedMachine.goStopped();
    }

    public double usedMemory(String machineName) {
        double machineUsedMemory = 0.0;

        CloudMachine cloudMachine= findMachineByMachineName(machineName);

        if(cloudMachine.isRunning()) {
            machineUsedMemory = cloudMachine.usedMemory();
        }

        return machineUsedMemory;
    }

    /**
     * Get the used space disk of a store or a machine
     * @param machineOrStoreName
     * @return
     */
    public double usedDisk(String machineOrStoreName) {
        double usedDisk;

        CloudMachine cloudMachine= findMachineByMachineName(machineOrStoreName);
        if(cloudMachine!=null){
            usedDisk = cloudMachine.usedDisk();
        }else {
            CloudStore cloudStore = findStoreByStoreName(machineOrStoreName);
            usedDisk = cloudStore.usedDisk();
        }

        return usedDisk;
    }

    /**
     * Get the global used space by virtual machines and stores
     * @return
     */
    public double globalUsedDisk() {
        double globalUsedDisk = 0.0;

        globalUsedDisk += this.stores.stream().mapToDouble(store-> usedDisk(store.getName())).sum();
        globalUsedDisk += this.machines.stream().mapToDouble(machine-> usedDisk(machine.getName())).sum();

        return globalUsedDisk;
    }

    /**
     * Get the global used memory by the running virtual machines
     * @return
     */
    public double globalUsedMemory() {
        double machinesGlobalUsedMemory = 0.0;

        machinesGlobalUsedMemory += this.machines.stream().mapToDouble(machine-> usedMemory(machine.getName())).sum();

        return machinesGlobalUsedMemory;
    }
}
