package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import LR4.INFO.OnEndData;
import LR4.INFO.PricesForDistributerData;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class SavePricesParallel extends ParallelBehaviour {
    private Behaviour receiver,waker;
    PricesForDistributerData pricesForDistributerData;
    private OnEndData onEnd = new OnEndData();

    public SavePricesParallel(Agent a, PricesForDistributerData pricesForDistributerData, long wakeUpTime) {
        super(a, WHEN_ANY);
        this.pricesForDistributerData = pricesForDistributerData;
        receiver = new ListPricesSave(getAgent(), pricesForDistributerData, onEnd);
        waker = new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                System.out.println(getAgent().getLocalName()+" заканчиваем торги");
                super.onWake();
            }
        };
        addSubBehaviour(receiver);
        addSubBehaviour(waker);
    }

    @Override
    public int onEnd() {
        return onEnd.getOnEnd();
    }
}
