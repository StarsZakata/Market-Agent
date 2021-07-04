package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import LR4.INFO.NeedsData;
import LR4.DF_Time_XML.DfHelper;
import LR4.Pro_Topic.Protocols;
import LR4.Pro_Topic.Services;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class StartTopicMsg extends OneShotBehaviour {
    NeedsData needsData;
    private Services topicname;

    public StartTopicMsg(Agent a, NeedsData needsData) {
        super(a);
        this.needsData = needsData;
    }

    @Override
    public void action() {
        topicname = Services.AgentsInTradeFirst;

        List<AID> agents = DfHelper.searchForAgents(topicname.toString(), getAgent());
        agents.remove(getAgent().getAID());

        ACLMessage needs = new ACLMessage(ACLMessage.REQUEST);
        needs.setContent(String.valueOf(needsData.getkWh()));
        for (AID agent : agents) {
            needs.addReceiver(agent);
        }
        needs.setProtocol(Protocols.LetsStartTrade);
        getAgent().send(needs);

    }
}
