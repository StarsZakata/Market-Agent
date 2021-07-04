package LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE;

import LR4.Behaviours.BehavioursForDistributer.DistributerFSM;
import LR4.INFO.NeedsData;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class SellerAlreadySold extends OneShotBehaviour {
    NeedsData needsData;

    public SellerAlreadySold(Agent a, NeedsData needsData) {
        super(a);
        this.needsData = needsData;
    }

    @Override
    public void action() {
        getAgent().addBehaviour(new DistributerFSM(getAgent(), needsData));
    }
}
