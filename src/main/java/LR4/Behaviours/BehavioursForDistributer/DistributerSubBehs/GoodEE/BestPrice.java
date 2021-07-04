package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import LR4.INFO.NeedsData;
import LR4.INFO.PriceWithNameForDistributerData;
import LR4.INFO.PricesForDistributerData;
import LR4.Pro_Topic.Protocols;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class BestPrice extends OneShotBehaviour {
    PricesForDistributerData pricesForDistributerData;
    PriceWithNameForDistributerData bestPrice;
    NeedsData needsData;
    int onEnd;

    public BestPrice(Agent a, PricesForDistributerData pricesForDistributerData, PriceWithNameForDistributerData bestPrice, NeedsData needsData) {
        super(a);
        this.pricesForDistributerData = pricesForDistributerData;
        this.bestPrice = bestPrice;
        this.needsData = needsData;
    }

    @Override
    public int onEnd() {
        return onEnd;
    }

    @Override
    public void action() {
        int mini = 0;
        double minprice = pricesForDistributerData.getPricesWithNames().get(0).getPrice();
        for (int i = 1; i < pricesForDistributerData.getPricesWithNames().size(); i++) {
            if (minprice >= pricesForDistributerData.getPricesWithNames().get(i).getPrice()) {
                minprice = pricesForDistributerData.getPricesWithNames().get(i).getPrice();
                mini = i;
            }
        }
        bestPrice.setPrice(pricesForDistributerData.getPricesWithNames().get(mini).getPrice());
        bestPrice.setName(pricesForDistributerData.getPricesWithNames().get(mini).getName());
        if (bestPrice.getPrice() > needsData.getMaxPrice()) {
            onEnd = 2;
        } else {
            ACLMessage win = new ACLMessage(ACLMessage.INFORM);
            win.addReceiver(new AID(bestPrice.getName(), false));
            win.setProtocol(Protocols.YouWon);
            getAgent().send(win);
            onEnd =1;
        }
    }
}
