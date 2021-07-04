package LR4;

import LR4.Behaviours.BehavioursForConsumer.ConsumerFSM;
import LR4.Behaviours.BehavioursForConsumer.DataConsumer;
import LR4.INFO.ConsumerData;
import jade.core.Agent;

public class ConsumerAgent extends Agent {

    ConsumerData consumerData = new ConsumerData();

    @Override
    protected void setup() {


        addBehaviour(new DataConsumer(this, consumerData));

        addBehaviour(new ConsumerFSM(this, consumerData));

        super.setup();
    }
}
