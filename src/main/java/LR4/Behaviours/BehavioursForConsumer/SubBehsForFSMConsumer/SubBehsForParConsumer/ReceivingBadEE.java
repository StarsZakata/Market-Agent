package LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SubBehsForParConsumer;

import LR4.Pro_Topic.Protocols;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ReceivingBadEE extends Behaviour {
    int onEnd = 1;

    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.IBoughtEnergyAfterDivision));
        ACLMessage myReply = getAgent().receive( reply );
        if ( myReply != null) {
            System.out.println(getAgent().getLocalName()+" получил разделенную ЭЭ");
            onEnd = 2;
        }
    }

    @Override
    public boolean done() {
        return onEnd == 2;
    }
}
