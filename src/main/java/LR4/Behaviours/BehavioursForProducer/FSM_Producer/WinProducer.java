package LR4.Behaviours.BehavioursForProducer.FSM_Producer;

import LR4.INFO.ProducerData;
import LR4.Pro_Topic.Protocols;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class WinProducer extends OneShotBehaviour {
    private String distributersName;
    private double neededLoad;
    private ProducerData producerData;

    public WinProducer(Agent a, String distributersName, double neededLoad, ProducerData producerData) {
        super(a);
        this.distributersName = distributersName;
        this.neededLoad = neededLoad;
        this.producerData = producerData;
    }

    @Override
    public void action() {
        if (producerData.getStorage().get(getAgent().getAID()) >= neededLoad) {
            producerData.getStorage().put(getAgent().getAID(), producerData.getStorage().get(getAgent().getAID()) - neededLoad);
            ACLMessage confirm = new ACLMessage(ACLMessage.INFORM);
            confirm.addReceiver(new AID(distributersName, false));
            confirm.setProtocol(Protocols.ConfirmSelling);
            getAgent().send(confirm);
            System.out.println(getAgent().getLocalName()+" продал ЭЭ "+distributersName);
        } else {
            System.out.println(getAgent().getLocalName()+" не осталось ЭЭ");
        }
    }
}
