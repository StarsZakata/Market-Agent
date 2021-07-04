package LR4.Behaviours.BehavioursForProducer.StorageData;

import LR4.INFO.ProducerData;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class TPP_StorageTicker extends TickerBehaviour {
    ProducerData producerData;

    public TPP_StorageTicker(Agent a, long period, ProducerData producerData) {
        super(a, period);
        this.producerData = producerData;
    }

    @Override
    protected void onTick() {
        double agentValue = producerData.getStorage().get(getAgent().getAID());
        double value = 23.5;
        producerData.getStorage().put(getAgent().getAID(), agentValue + value);
        System.out.println(getAgent().getLocalName()+" в хранилище "+producerData.getStorage().get(getAgent().getAID()));

    }
}
