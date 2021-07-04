package LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer;

import LR4.Behaviours.BehavioursForConsumer.ConsumerFSM;
import LR4.INFO.ConsumerData;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class FSMStarter extends OneShotBehaviour {
    ConsumerData consumerData;

    public FSMStarter(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;
    }

    @Override
    public void action() {
        getAgent().addBehaviour(new ConsumerFSM(getAgent(), consumerData));
    }
}
