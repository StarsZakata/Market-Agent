package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.BadEE;

import LR4.INFO.OnEndData;
import LR4.INFO.PricesForDistributerData;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class ReceivingPricesAfterDivisionPar extends ParallelBehaviour {
    private Behaviour receiver,waker;
    PricesForDistributerData pricesForDistributerDataAfterDivision;
    private OnEndData onEnd = new OnEndData();

    public ReceivingPricesAfterDivisionPar(Agent a, PricesForDistributerData pricesForDistributerDataAfterDivision, long wakeUpTime) {
        super(a, WHEN_ANY);
        this.pricesForDistributerDataAfterDivision = pricesForDistributerDataAfterDivision;
        receiver = new ReceivingPricesAfterDivisionForParallelBeh(getAgent(), pricesForDistributerDataAfterDivision, onEnd);
        waker = new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                System.out.println(getAgent().getLocalName()+" оконачание торгов");
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
