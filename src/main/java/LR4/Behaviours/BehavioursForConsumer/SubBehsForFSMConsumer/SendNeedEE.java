package LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer;

import LR4.Configs.ConsumerConfig;
import LR4.INFO.ConsumerData;
import LR4.DF_Time_XML.TimeHelper;
import LR4.DF_Time_XML.XmlHelper;
import LR4.Pro_Topic.Protocols;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;

public class SendNeedEE extends WakerBehaviour {
    ConsumerData consumerData;

    public SendNeedEE(Agent a, int wakeupDate, ConsumerData consumerData) {
        super(a, wakeupDate);
        this.consumerData = consumerData;
    }

    @Override
    protected void onWake() {

        ConsumerConfig consumerConfig = XmlHelper.unMarshalAny(ConsumerConfig.class, getAgent().getLocalName()+".xml");
        List<Double> kWhPerHour = consumerConfig.getkWhPerHour();
        System.out.println(getAgent().getLocalName()+" необходимо ЭЭ в  "+kWhPerHour.get(TimeHelper.getCurrentHour())+" сейчас время "+TimeHelper.getCurrentHour()+":00 O'Clock");
        ACLMessage needs = new ACLMessage(ACLMessage.REQUEST);
        needs.setContent(kWhPerHour.get(TimeHelper.getCurrentHour())+","+consumerData.getMaxPrice().get(getAgent().getAID()));
        needs.addReceiver(new AID(consumerConfig.getDistributersName(), false));
        needs.setProtocol(Protocols.SentNeeds);
        getAgent().send(needs);

        super.onWake();
    }
}
