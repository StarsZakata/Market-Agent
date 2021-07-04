package LR4.Behaviours.BehavioursForDistributer;

import LR4.INFO.NeedsData;
import LR4.Pro_Topic.Protocols;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class DistributerBehaviour extends Behaviour{
    @Override
    public void action() {

        MessageTemplate needs = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.MatchProtocol(Protocols.SentNeeds));
        ACLMessage needsFromConsumer = getAgent().receive( needs );
        if (needsFromConsumer != null) {
            NeedsData needsData = new NeedsData();
            String[] parsedNeeds = needsFromConsumer.getContent().split(",");
            double kWh = Double.parseDouble(parsedNeeds[0]);
            double maxPrice = Double.parseDouble(parsedNeeds[1]);
            needsData.setkWh(kWh);
            needsData.setMaxPrice(maxPrice);
            getAgent().addBehaviour(new DistributerFSM(getAgent(), needsData));
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
