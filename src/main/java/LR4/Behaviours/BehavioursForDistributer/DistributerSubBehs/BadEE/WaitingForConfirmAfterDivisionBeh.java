package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.BadEE;

import LR4.INFO.OnEndData;
import LR4.Pro_Topic.Protocols;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

public class WaitingForConfirmAfterDivisionBeh extends Behaviour {
    private OnEndData onEnd;

    public WaitingForConfirmAfterDivisionBeh(Agent a, ArrayList<String> confirmedProducers, OnEndData onEnd) {
        super(a);
        this.confirmedProducers = confirmedProducers;
        this.onEnd = onEnd;
    }

    ArrayList<String> confirmedProducers;

    @Override
    public void action() {
        MessageTemplate informConfirm = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.ConfirmSelling));
        ACLMessage congratulations = getAgent().receive( informConfirm );
        if ( congratulations != null) {
            confirmedProducers.add(congratulations.getSender().getLocalName());
            onEnd.setOnEnd(1);
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
