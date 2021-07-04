package LR4.Behaviours.BehavioursForConsumer;

import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.FSMStarter;
import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.ReceivingAnswerParrallel_4;
import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SendNeedEE;
import LR4.INFO.ConsumerData;
import LR4.INFO.OnEndData;
import LR4.DF_Time_XML.TimeHelper;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

public class ConsumerFSM extends FSMBehaviour {
    private final String
            SENDREQ = "Send_Req",
            RECEIVINGANSWER = "Receiving_Answer",
            ONCEAGAIN = "Once_Again";
    private ConsumerData consumerData;
    OnEndData onEndData = new OnEndData();

    public ConsumerFSM(Agent a, ConsumerData consumerData) {
        super(a);
        this.consumerData = consumerData;

        registerFirstState(new SendNeedEE(getAgent(), TimeHelper.getMsBeforeNextHour()+500, consumerData), SENDREQ);
        registerState(new ReceivingAnswerParrallel_4(getAgent(), consumerData, onEndData), RECEIVINGANSWER);
        registerLastState(new FSMStarter(getAgent(), consumerData), ONCEAGAIN);

        registerDefaultTransition(SENDREQ, RECEIVINGANSWER);
        registerDefaultTransition(RECEIVINGANSWER, ONCEAGAIN);
    }
}
