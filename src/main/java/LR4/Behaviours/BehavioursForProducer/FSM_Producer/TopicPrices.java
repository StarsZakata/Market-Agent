package LR4.Behaviours.BehavioursForProducer.FSM_Producer;

import LR4.INFO.ProducerData;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class TopicPrices extends ParallelBehaviour {
    private Behaviour receiver, waker;
    private double neededLoad;
    private ProducerData producerData;
    private String distributersName;

    public TopicPrices(Agent a, double neededLoad, ProducerData producerData, String distributersName) {
        super(a, WHEN_ANY);
        this.neededLoad = neededLoad;
        this.producerData = producerData;
        this.distributersName = distributersName;

        receiver = new SendingNewPriceMessage(getAgent(), neededLoad, producerData, distributersName);
        waker = new WakerBehaviour(getAgent(), 10000) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        };
        addSubBehaviour(receiver);
        addSubBehaviour(waker);
    }
}
