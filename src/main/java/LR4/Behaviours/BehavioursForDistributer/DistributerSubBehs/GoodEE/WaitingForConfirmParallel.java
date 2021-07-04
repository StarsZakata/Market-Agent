package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class WaitingForConfirmParallel extends ParallelBehaviour {
    private Behaviour receiver, waker;

    @Override
    public int onEnd() {
        if (receiver.done()) {
            return 1;
        }  else {
            return 2;
        }
    }
    public WaitingForConfirmParallel(Agent a, long wakeUpTime) {
        super(a, WHEN_ANY);

        receiver = new WaitingForConfirm();
        waker = new WakerBehaviour(getAgent(), wakeUpTime) {
            @Override
            protected void onWake() {
                System.out.println(getAgent().getLocalName()+" произовдитель продал ЭЭ");
                super.onWake();
            }
        };
        addSubBehaviour(receiver);
        addSubBehaviour(waker);
    }
}
