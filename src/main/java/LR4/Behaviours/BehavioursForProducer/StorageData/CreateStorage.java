package LR4.Behaviours.BehavioursForProducer.StorageData;

import LR4.INFO.ProducerData;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class CreateStorage extends OneShotBehaviour {
    ProducerData producerData;

    public CreateStorage(Agent a, ProducerData producerData) {
        super(a);
        this.producerData = producerData;
    }

    @Override
    public void action() {
        producerData.getStorage().put(getAgent().getAID(), 0.0);
        System.out.println(getAgent().getLocalName()+" создано хранилище");
        }
    }

