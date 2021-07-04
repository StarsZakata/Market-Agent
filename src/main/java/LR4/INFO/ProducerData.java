package LR4.INFO;

import jade.core.AID;

import java.util.HashMap;

public class ProducerData {

    private HashMap<AID, Double> storage = new HashMap<AID, Double>();

    public HashMap<AID, Double> getStorage() {
        return storage;
    }
    public void setStorage(HashMap<AID, Double> storage) {
        this.storage = storage;
    }
}
