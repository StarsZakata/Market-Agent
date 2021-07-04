package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.BadEE;

import LR4.INFO.NeedsData;
import LR4.Configs.DistributerConfig;
import LR4.Pro_Topic.Protocols;
import LR4.DF_Time_XML.XmlHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;

public class IBoughtEnergyAfterDivisionOneShot extends OneShotBehaviour {
    NeedsData needsData;
    ArrayList<String> confirmedProducersAfterDivision;

    public IBoughtEnergyAfterDivisionOneShot(Agent a, ArrayList<String> confirmedProducersAfterDivision, NeedsData needsData) {
        super(a);
        this.confirmedProducersAfterDivision = confirmedProducersAfterDivision;
        this.needsData = needsData;
    }

    @Override
    public void action() {
        DistributerConfig cfg = XmlHelper.unMarshalAny(DistributerConfig.class, getAgent().getLocalName()+".xml");
        ACLMessage dividedWin = new ACLMessage(ACLMessage.INFORM);
        dividedWin.addReceiver(new AID(cfg.getProducersName(), false));
        dividedWin.setProtocol(Protocols.IBoughtEnergyAfterDivision);
        getAgent().send(dividedWin);
        System.out.println(getAgent().getLocalName()+" покупаем "+(needsData.getkWh()*confirmedProducersAfterDivision.size()/3)+" от "+confirmedProducersAfterDivision);
    }
}
