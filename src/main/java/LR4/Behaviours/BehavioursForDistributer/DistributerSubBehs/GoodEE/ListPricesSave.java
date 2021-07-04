package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import LR4.INFO.OnEndData;
import LR4.INFO.PriceWithNameForDistributerData;
import LR4.INFO.PricesForDistributerData;
import LR4.Pro_Topic.Protocols;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;

public class ListPricesSave extends Behaviour {
    PricesForDistributerData pricesForDistributerData;
    private OnEndData onEnd;

    public ListPricesSave(Agent a, PricesForDistributerData pricesForDistributerData, OnEndData onEnd) {
        super(a);
        this.pricesForDistributerData = pricesForDistributerData;
        this.onEnd = onEnd;
    }

    @Override
    public void action() {
        MessageTemplate price = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchProtocol(Protocols.PriceFromProducer));
        ACLMessage priceFromProducer = getAgent().receive( price );
        if (priceFromProducer != null) {
            double hisPrice = Double.parseDouble(priceFromProducer.getContent());
            ArrayList<PriceWithNameForDistributerData> currentPrices = pricesForDistributerData.getPricesWithNames();
            currentPrices.add(new PriceWithNameForDistributerData(hisPrice, priceFromProducer.getSender().getLocalName()));
            pricesForDistributerData.setPricesWithNames(currentPrices);
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
