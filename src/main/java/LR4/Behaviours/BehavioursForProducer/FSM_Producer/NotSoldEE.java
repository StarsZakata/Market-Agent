package LR4.Behaviours.BehavioursForProducer.FSM_Producer;

import jade.core.behaviours.OneShotBehaviour;

public class NotSoldEE extends OneShotBehaviour {



    @Override
    public void action() {
        System.out.println(getAgent().getLocalName()+" не продал ЭЭ");
    }
}
