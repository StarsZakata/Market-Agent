package LR4.Behaviours.BehavioursForProducer.StorageData;

import LR4.Configs.WindConfig;
import LR4.INFO.ProducerData;
import LR4.DF_Time_XML.TimeHelper;
import LR4.DF_Time_XML.XmlHelper;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.ArrayList;

public class Wind_StorageTicker extends TickerBehaviour {
    ProducerData producerData;

    public Wind_StorageTicker(Agent a, long period, ProducerData producerData) {
        super(a, period);
        this.producerData = producerData;
    }

    @Override
    protected void onTick() {
        double agentValue = producerData.getStorage().get(getAgent().getAID());

        WindConfig windConfig = XmlHelper.unMarshalAny(WindConfig.class , "Wind.xml");

        ArrayList<Double> production = windConfig.getProduction();
        double value = production.get(TimeHelper.getCurrentHour());


        producerData.getStorage().put(getAgent().getAID(), agentValue + value);
        System.out.println(getAgent().getLocalName()+" в хранилище "+producerData.getStorage().get(getAgent().getAID()));

    }
}
