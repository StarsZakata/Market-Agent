package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import LR4.Configs.DistributerConfig;
import LR4.Pro_Topic.Protocols;
import LR4.DF_Time_XML.XmlHelper;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class NeedBadEE_Message extends OneShotBehaviour {

    @Override
    public void action() {
        DistributerConfig cfg = XmlHelper.unMarshalAny(DistributerConfig.class, getAgent().getLocalName()+".xml");
        ACLMessage minPrice = new ACLMessage(ACLMessage.INFORM);
        minPrice.addReceiver(new AID(cfg.getProducersName(), false));
        minPrice.setProtocol(Protocols.IBoughtEnergy);
        getAgent().send(minPrice);
    }
}
