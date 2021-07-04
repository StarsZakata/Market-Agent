package LR4.AdvancedTests.BehsForTest;

import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.ReceivingAnswerParrallel_4;
import LR4.INFO.ConsumerData;
import LR4.INFO.OnEndData;
import LR4.DF_Time_XML.TimeHelper;
import LR4.Pro_Topic.Protocols;
import jade.core.AID;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class TestForConsumer extends FSMBehaviour {
    private final String SENDREQ = "Send_Req", RECEIVINGANSWER = "Receiving_Answer";
    private ConsumerData consumerData;
    OnEndData onEndData = new OnEndData();
    double load;
    double maxPrice;

    @Override
    public int onEnd() {
        return onEndData.getOnEnd();
    }

    public TestForConsumer(ConsumerData consumerData, double load, double maxPrice) {
        this.consumerData = consumerData;
        this.load = load;
        this.maxPrice = maxPrice;

        registerFirstState(new WakerBehaviour(getAgent(), TimeHelper.getMsBeforeNextHour()+500) {
            @Override
            protected void onWake() {
                System.out.println(getAgent().getLocalName()+" необходимо ЭЭ "+load);
                ACLMessage needs = new ACLMessage(ACLMessage.REQUEST);
                needs.setContent(load+","+maxPrice);
                needs.addReceiver(new AID("FoodDistributer", false));
                needs.setProtocol(Protocols.SentNeeds);
                getAgent().send(needs);
            }
        }, SENDREQ);
        registerLastState(new ReceivingAnswerParrallel_4(getAgent(), consumerData, onEndData), RECEIVINGANSWER);

        registerDefaultTransition(SENDREQ, RECEIVINGANSWER);
    }
}
