package LR4.Behaviours.BehavioursForProducer;

import LR4.Behaviours.BehavioursForProducer.FSM_Producer.*;
import LR4.INFO.ProducerData;
import jade.core.Agent;

public class ProduserFSM extends jade.core.behaviours.FSMBehaviour {
    private final String
            SENDINGPRICE = "Sending_Price",
            RECEIVINGPRICE = "Receiving_Price",
            NOITEMS = "No_Items",
            WAITINGFORDECISION = "Waiting_For_Decision",
            AFTERWIN = "After_Win",
            AFTERLOST = "After_Lost";
    private String distributersName;
    private double neededLoad;
    private ProducerData producerData;

    public ProduserFSM(Agent a, String distributersName, double neededLoad, ProducerData producerData) {
        super(a);
        this.distributersName = distributersName;
        this.neededLoad = neededLoad;
        this.producerData = producerData;

        registerFirstState(new SendingPriceMessage(getAgent(), neededLoad, producerData, distributersName), SENDINGPRICE);
        registerState(new TopicPrices(getAgent(), neededLoad, producerData, distributersName), RECEIVINGPRICE);
        registerState(new WaitingParallel(getAgent(), 3000, distributersName), WAITINGFORDECISION);
        registerLastState(new WinProducer(getAgent(), distributersName, neededLoad, producerData), AFTERWIN);
        registerLastState(new NotEE(), NOITEMS);
        registerLastState(new NotSoldEE(), AFTERLOST);

        registerTransition(SENDINGPRICE, RECEIVINGPRICE, 1);
        registerTransition(SENDINGPRICE, NOITEMS, 2);
        registerDefaultTransition(RECEIVINGPRICE, WAITINGFORDECISION);
        registerTransition(WAITINGFORDECISION, AFTERWIN, 1);
        registerTransition(WAITINGFORDECISION, AFTERLOST, 2);
    }


}
