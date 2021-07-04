package LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SubBehsForParConsumer;

import LR4.INFO.ConsumerData;
import LR4.Pro_Topic.Protocols;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class BadMyPrice extends Behaviour {
    ConsumerData consumerData;
    int onEnd = 1;

    public BadMyPrice(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;
    }

    @Override
    public void action() {
        MessageTemplate reply = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.MaxPriceTooLow));
        ACLMessage myReply = getAgent().receive( reply );
        if (myReply != null) {
            double lowestPriceInTrade = Double.parseDouble(myReply.getContent());
            consumerData.getMaxPrice().put(getAgent().getAID(), lowestPriceInTrade);
            System.out.println(getAgent().getLocalName()+" цена оказалось слишком низкой "+lowestPriceInTrade);
            onEnd = 2;
        }
    }

    @Override
    public boolean done() {
        return onEnd == 2;
    }
}
