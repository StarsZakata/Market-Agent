package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.BadEE;

import LR4.Configs.DistributerConfig;
import LR4.INFO.MostMinPriceAfterDivisionData;
import LR4.Pro_Topic.Protocols;
import LR4.DF_Time_XML.XmlHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class MinPriceTooLargeAfterDivisionOneShotBeh extends OneShotBehaviour {
    MostMinPriceAfterDivisionData mostMinPriceAfterDivision;

    public MinPriceTooLargeAfterDivisionOneShotBeh(Agent a, MostMinPriceAfterDivisionData mostMinPriceAfterDivision) {
        super(a);
        this.mostMinPriceAfterDivision = mostMinPriceAfterDivision;
    }

    @Override
    public void action() {
        DistributerConfig cfg = XmlHelper.unMarshalAny(DistributerConfig.class, getAgent().getLocalName()+".xml");
        ACLMessage minPrice = new ACLMessage(ACLMessage.INFORM);
        minPrice.setContent(String.valueOf(mostMinPriceAfterDivision.getMostMinPrice()));
        minPrice.addReceiver(new AID(cfg.getProducersName(), false));
        minPrice.setProtocol(Protocols.MaxPriceTooLow);
        getAgent().send(minPrice);

    }
}
