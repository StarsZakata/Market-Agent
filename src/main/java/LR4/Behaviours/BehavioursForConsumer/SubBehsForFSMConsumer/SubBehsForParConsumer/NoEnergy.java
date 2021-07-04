package LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SubBehsForParConsumer;

import LR4.Pro_Topic.Protocols;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class NoEnergy extends Behaviour {
    int onEnd = 1;

    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.TheyDontHaveEnergy));
        ACLMessage myReply = getAgent().receive( reply );
        if (myReply != null) {
            System.out.println(getAgent().getLocalName()+": They don't have any energy for me :(");
            onEnd = 2;
        }
    }

    @Override
    public boolean done() {
        return onEnd == 2;
    }
}
