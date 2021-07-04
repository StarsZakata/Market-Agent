package LR4.Behaviours.BehavioursForConsumer;

import LR4.INFO.ConsumerData;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class DataConsumer extends OneShotBehaviour {
    ConsumerData consumerData;

    public DataConsumer(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;
    }

    @Override
    public void action() {

        consumerData.getMaxPrice().put(getAgent().getAID(), 100.0);
    }
}
