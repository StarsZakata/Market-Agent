package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.BadEE;

import LR4.INFO.MostMinPriceAfterDivisionData;
import LR4.INFO.NeedsData;
import LR4.INFO.PricesForDistributerData;
import LR4.Pro_Topic.Protocols;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class MinPricesAfterDivisionOneShotBeh extends OneShotBehaviour {
    NeedsData needsData;
    PricesForDistributerData pricesForDistributerDataAfterDivision;
    MostMinPriceAfterDivisionData mostMinPriceAfterDivision;

    ArrayList<Double> firstProducerPrices = new ArrayList<>();
    ArrayList<Double> secondProducerPrices = new ArrayList<>();
    ArrayList<Double> thirdProducerPrices = new ArrayList<>();
    private int onEnd = 2;
    ArrayList<Double> allMinPricesAfterDivision = new ArrayList<>();

    public MinPricesAfterDivisionOneShotBeh(Agent a, PricesForDistributerData pricesForDistributerDataAfterDivision, MostMinPriceAfterDivisionData mostMinPriceAfterDivision, NeedsData needsData) {
        super(a);
        this.pricesForDistributerDataAfterDivision = pricesForDistributerDataAfterDivision;
        this.mostMinPriceAfterDivision = mostMinPriceAfterDivision;
        this.needsData = needsData;
    }

    @Override
    public int onEnd() {
        return onEnd;
    }

    @Override
    public void action() {
        for (int i = 0; i < pricesForDistributerDataAfterDivision.getPricesWithNames().size(); i++) {
            if (pricesForDistributerDataAfterDivision.getPricesWithNames().get(i).getName().equals("TPP")) {
                firstProducerPrices.add(pricesForDistributerDataAfterDivision.getPricesWithNames().get(i).getPrice());
            }
            if (pricesForDistributerDataAfterDivision.getPricesWithNames().get(i).getName().equals("SES")) {
                secondProducerPrices.add(pricesForDistributerDataAfterDivision.getPricesWithNames().get(i).getPrice());
            }
            if (pricesForDistributerDataAfterDivision.getPricesWithNames().get(i).getName().equals("Wind")) {
                thirdProducerPrices.add(pricesForDistributerDataAfterDivision.getPricesWithNames().get(i).getPrice());
            }
        }
        if (firstProducerPrices.size() > 0) {
            double minprice = firstProducerPrices.get(0);
            for (int i = 1; i < firstProducerPrices.size(); i++) {
                if (minprice >= firstProducerPrices.get(i)) {
                    minprice = firstProducerPrices.get(i);
                }
            } allMinPricesAfterDivision.add(minprice);
            if (minprice <= needsData.getMaxPrice()) {
                onEnd = 1;
                ACLMessage win = new ACLMessage(ACLMessage.INFORM);
                win.addReceiver(new AID("TPP", false));
                win.setProtocol(Protocols.YouWon);
                getAgent().send(win);
            }
        }
        if (secondProducerPrices.size() > 0) {
            double minprice = secondProducerPrices.get(0);
            for (int i = 1; i < secondProducerPrices.size(); i++) {
                if (minprice >= secondProducerPrices.get(i)) {
                    minprice = secondProducerPrices.get(i);
                }
            } allMinPricesAfterDivision.add(minprice);
            if (minprice <= needsData.getMaxPrice()) {
                onEnd = 1;
                ACLMessage win = new ACLMessage(ACLMessage.INFORM);
                win.addReceiver(new AID("SES", false));
                win.setProtocol(Protocols.YouWon);
                getAgent().send(win);
            }
        }
        if (thirdProducerPrices.size() > 0) {
            double minprice = thirdProducerPrices.get(0);
            for (int i = 1; i < thirdProducerPrices.size(); i++) {
                if (minprice >= thirdProducerPrices.get(i)) {
                    minprice = thirdProducerPrices.get(i);
                }
            } allMinPricesAfterDivision.add(minprice);
            if (minprice <= needsData.getMaxPrice()) {
                onEnd = 1;
                ACLMessage win = new ACLMessage(ACLMessage.INFORM);
                win.addReceiver(new AID("Wind", false));
                win.setProtocol(Protocols.YouWon);
                getAgent().send(win);
            }
        }
        mostMinPriceAfterDivision.setMostMinPrice(allMinPricesAfterDivision.get(0));
        for (int i = 1; i < allMinPricesAfterDivision.size(); i++) {
            if (mostMinPriceAfterDivision.getMostMinPrice() >= allMinPricesAfterDivision.get(i)) {
                mostMinPriceAfterDivision.setMostMinPrice(allMinPricesAfterDivision.get(i));
            }
        }
    }
}
