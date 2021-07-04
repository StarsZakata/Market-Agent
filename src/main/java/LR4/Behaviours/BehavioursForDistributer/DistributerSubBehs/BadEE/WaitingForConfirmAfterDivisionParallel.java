package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.BadEE;

import LR4.INFO.OnEndData;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

import java.util.ArrayList;

public class WaitingForConfirmAfterDivisionParallel extends ParallelBehaviour {
    ArrayList<String> confirmedProducersAfterDivision;
    private Behaviour receiver, waker;
    private OnEndData onEnd = new OnEndData();

    @Override
    public int onEnd() {
        return onEnd.getOnEnd();
    }

    public WaitingForConfirmAfterDivisionParallel(Agent a, long wakeUpTime, ArrayList<String> confirmedProducersAfterDivision) {
        super(a, WHEN_ANY);
        this.confirmedProducersAfterDivision = confirmedProducersAfterDivision;

        receiver = new WaitingForConfirmAfterDivisionBeh(getAgent(), confirmedProducersAfterDivision, onEnd);
        waker = new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        };
        addSubBehaviour(receiver);
        addSubBehaviour(waker);
    }
}
