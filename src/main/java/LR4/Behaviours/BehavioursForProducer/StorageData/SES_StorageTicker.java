package LR4.Behaviours.BehavioursForProducer.StorageData;

import LR4.Configs.SESConfig;
import LR4.INFO.ProducerData;
import LR4.DF_Time_XML.TimeHelper;
import LR4.DF_Time_XML.XmlHelper;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.ArrayList;

public class SES_StorageTicker extends TickerBehaviour {
    ProducerData producerData;

    public SES_StorageTicker(Agent a, long period, ProducerData producerData) {
        super(a, period);
        this.producerData = producerData;
    }

    @Override
    protected void onTick() {
        double agentValue = producerData.getStorage().get(getAgent().getAID());
        SESConfig storageConfig = XmlHelper.unMarshalAny(SESConfig.class, "SES.xml");
        ArrayList<Double> storageFromConfig = storageConfig.getConfigForSES();
        Double[] production = new Double[24];
        for (int i = 0; i < 24; i++) {
            if (i <= 5 || i >= 19) {
                production[i] = 0.0;
            } else {
                production[i] = 0.0;
                for (int j = 0; j < storageFromConfig.size(); j++) {
                    production[i] += storageFromConfig.get(j) * Math.pow(i, j);
                }
            }
        }
        double value = production[TimeHelper.getCurrentHour()];
        producerData.getStorage().put(getAgent().getAID(), agentValue + value);
        System.out.println(getAgent().getLocalName()+" в хранилище "+producerData.getStorage().get(getAgent().getAID()));

    }
}
