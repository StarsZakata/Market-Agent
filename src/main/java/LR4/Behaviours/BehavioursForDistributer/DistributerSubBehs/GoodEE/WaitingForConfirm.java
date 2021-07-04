package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import LR4.Pro_Topic.Protocols;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WaitingForConfirm extends Behaviour {
    private boolean confirm;

    @Override
    public void action() {
        MessageTemplate informConfirm = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.ConfirmSelling));
        ACLMessage congratulations = getAgent().receive( informConfirm );
        if ( congratulations != null) {
            confirm = true;
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return confirm;
    }
}
