package LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer;

import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SubBehsForParConsumer.BadMyPrice;
import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SubBehsForParConsumer.ReceivingBadEE;
import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SubBehsForParConsumer.ReceivingGoodEE;
import LR4.Behaviours.BehavioursForConsumer.SubBehsForFSMConsumer.SubBehsForParConsumer.NoEnergy;
import LR4.INFO.ConsumerData;
import LR4.INFO.OnEndData;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;

public class ReceivingAnswerParrallel_4 extends ParallelBehaviour {
    private Behaviour priceTooLow, receiveEnergy, haveNoEnergy, receiveEnergyAfterDivision;
    private ConsumerData consumerData;
    OnEndData onEndData;

    @Override
    public int onEnd() {
        if (priceTooLow.done()) {
            onEndData.setOnEnd(2);
        } if (receiveEnergy.done()) {
            onEndData.setOnEnd(1);
        } if (haveNoEnergy.done()){
            onEndData.setOnEnd(3);
        } if (receiveEnergyAfterDivision.done()) {
            onEndData.setOnEnd(4);
        }
        return super.onEnd();
    }

    public ReceivingAnswerParrallel_4(Agent a, ConsumerData consumerData, OnEndData onEndData) {
        super(a, WHEN_ANY);
        this.consumerData = consumerData;
        this.onEndData = onEndData;

        receiveEnergyAfterDivision = new ReceivingBadEE();
        priceTooLow = new BadMyPrice(getAgent(), consumerData);
        receiveEnergy = new ReceivingGoodEE();
        haveNoEnergy = new NoEnergy();

        addSubBehaviour(receiveEnergyAfterDivision);
        addSubBehaviour(priceTooLow);
        addSubBehaviour(receiveEnergy);
        addSubBehaviour(haveNoEnergy);
    }
}
