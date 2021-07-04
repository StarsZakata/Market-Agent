package LR4.AdvancedTests;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class TestAgent extends Agent {
    @Override
    protected void setup() {

        System.out.println(this.getLocalName()+" родился ");
        Object[] arguments = getArguments();
        for (Object argument : arguments) {
            addBehaviour((Behaviour) argument);
        }
    }
}
