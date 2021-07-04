package LR4.Behaviours.BehavioursForProducer.FSM_Producer;

import LR4.Pro_Topic.Protocols;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WaitingWhyWin extends Behaviour {
    String distributersName;

    public WaitingWhyWin(String distributersName) {
        this.distributersName = distributersName;
    }

    private boolean iWon;

    @Override
    public void action() {
        MessageTemplate inform = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.YouWon));
        ACLMessage congratulations = getAgent().receive(inform);
        if (congratulations != null) {
            if (congratulations.getSender().getLocalName().equals(distributersName)) {
                iWon = true;
            } else { block(); }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return iWon;
    }
}
