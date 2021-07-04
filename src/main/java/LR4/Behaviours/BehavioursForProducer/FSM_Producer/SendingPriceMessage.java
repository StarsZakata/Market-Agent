package LR4.Behaviours.BehavioursForProducer.FSM_Producer;

import LR4.DF_Time_XML.DfHelper;
import LR4.INFO.ProducerData;
import LR4.Pro_Topic.Protocols;
import LR4.Pro_Topic.Services;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class SendingPriceMessage extends OneShotBehaviour {
    private double neededLoad;
    private ProducerData producerData;
    private String distributersName;

    private int onEnd;
    private Services topicname;

    public SendingPriceMessage(Agent a, double neededLoad, ProducerData producerData, String distributersName) {
        super(a);
        this.neededLoad = neededLoad;
        this.producerData = producerData;
        this.distributersName = distributersName;
    }

    @Override
    public int onEnd() {
        return onEnd;
    }

    @Override
    public void action() {
        topicname = Services.AgentsInTradeFirst;
        List<AID> agents = DfHelper.searchForAgents(topicname.toString(), getAgent());
        agents.remove(getAgent().getAID());
        if (neededLoad > producerData.getStorage().get(getAgent().getAID())) {
            onEnd = 2;
        } else {
            double myPrice = 1000/producerData.getStorage().get(getAgent().getAID());
            ACLMessage price = new ACLMessage(ACLMessage.INFORM);
            price.setContent(String.valueOf(myPrice));
            for (AID agent : agents) {
                price.addReceiver(agent);
            }
            price.addReceiver(new AID(distributersName, false));
            price.setProtocol(Protocols.PriceFromProducer);
            getAgent().send(price);
            System.out.println(getAgent().getLocalName()+" отправил свою цену="+myPrice);
            onEnd = 1;
        }
    }
}
