package LR4.Behaviours.BehavioursForProducer;

import LR4.INFO.ProducerData;
import LR4.Pro_Topic.Protocols;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class RecevingFirstMessage extends Behaviour {
    private ProducerData producerData;

    public RecevingFirstMessage(ProducerData producerData) {
        this.producerData = producerData;
    }

    @Override
    public void action() {
        MessageTemplate needs = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.MatchProtocol(Protocols.LetsStartTrade));
        ACLMessage needsFromDistributer = getAgent().receive( needs );
        if (needsFromDistributer != null) {
            double neededLoad = Double.parseDouble(needsFromDistributer.getContent());
            getAgent().addBehaviour(new ProduserFSM(getAgent(), needsFromDistributer.getSender().getLocalName(), neededLoad, producerData));
            System.out.println(getAgent().getLocalName()+" получили сообщение от "+needsFromDistributer.getSender().getLocalName());
        }
        else {
            block();
        }
    }

    @Override
    public boolean done() {
        return false;
    }
}
