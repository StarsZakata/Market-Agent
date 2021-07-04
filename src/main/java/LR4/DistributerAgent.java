package LR4;

import LR4.Behaviours.BehavioursForDistributer.DistributerBehaviour;
import jade.core.Agent;

public class DistributerAgent extends Agent {
    @Override
    protected void setup() {


        addBehaviour(new DistributerBehaviour());

        super.setup();
    }
}
