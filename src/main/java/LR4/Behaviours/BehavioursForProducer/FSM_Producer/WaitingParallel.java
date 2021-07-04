package LR4.Behaviours.BehavioursForProducer.FSM_Producer;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class WaitingParallel extends ParallelBehaviour {
    private Behaviour winning, waker;
    String distributersName;

    public WaitingParallel(Agent a, long wakeUpTime, String distributersName) {
        super(a, WHEN_ANY);
        this.distributersName = distributersName;


        winning = new WaitingWhyWin(distributersName);
        waker = new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                super.onWake();
            }
        };
        addSubBehaviour(winning);
        addSubBehaviour(waker);
    }

    @Override
    public int onEnd() {
        if (winning.done()) {
            return 1;
        }  else {
            return 2;
        }
    }
}
