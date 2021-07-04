package LR4.Behaviours.BehavioursForProducer.FSM_Producer;

import LR4.DF_Time_XML.DfHelper;
import LR4.INFO.ProducerData;
import LR4.Pro_Topic.Protocols;
import LR4.Pro_Topic.Services;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.List;

public class SendingNewPriceMessage extends Behaviour {
    private double neededLoad;
    private ProducerData producerData;
    private String distributersName;

    public SendingNewPriceMessage(Agent a, double neededLoad, ProducerData producerData, String distributersName) {
        super(a);
        this.neededLoad = neededLoad;
        this.producerData = producerData;
        this.distributersName = distributersName;
    }

    private Services topicname;



    @Override
    public void action() {
            topicname = Services.AgentsInTradeFirst;
        List<AID> agents = DfHelper.searchForAgents(topicname.toString(), getAgent());
        agents.remove(getAgent().getAID());

        MessageTemplate price = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.PriceFromProducer));
        ACLMessage priceFromProducer = getAgent().receive( price );
        if (priceFromProducer != null) {
            if (neededLoad <= producerData.getStorage().get(getAgent().getAID())) {
                double myPrice = 1000/producerData.getStorage().get(getAgent().getAID());
                double minPrice = myPrice/2;
                double enemyPrice = Double.parseDouble(priceFromProducer.getContent());
                double myNewPrice = 0.9 * enemyPrice;
                if (enemyPrice < myPrice) {
                if ( myNewPrice >= minPrice) {
                    ACLMessage newPrice = new ACLMessage(ACLMessage.INFORM);
                    newPrice.setContent(String.valueOf(myNewPrice));
                    for (AID agent : agents) {
                        newPrice.addReceiver(agent);
                    }
                    newPrice.addReceiver(new AID(distributersName, false));
                    newPrice.setProtocol(Protocols.PriceFromProducer);
                    getAgent().send(newPrice);
                    System.out.println(getAgent().getLocalName() + " моя новая цена=" + myNewPrice + " которая лучше чем у " + priceFromProducer.getSender().getLocalName());
                } else {
                    block();
                }
                } else {
                    block();
                }
            } else {
                block();
            }
        } else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
