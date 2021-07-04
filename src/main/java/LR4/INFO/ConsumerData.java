package LR4.INFO;

import jade.core.AID;

import java.util.HashMap;

public class ConsumerData {

    private HashMap<AID, Double> maxPrice = new HashMap<AID, Double>();

    public HashMap<AID, Double> getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(HashMap<AID, Double> maxPrice) {
        this.maxPrice = maxPrice;
    }
}
