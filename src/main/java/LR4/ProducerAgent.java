package LR4;

import LR4.Behaviours.BehavioursForProducer.RecevingFirstMessage;
import LR4.Behaviours.BehavioursForProducer.StorageData.CreateStorage;
import LR4.Behaviours.BehavioursForProducer.StorageData.SES_StorageTicker;
import LR4.Behaviours.BehavioursForProducer.StorageData.TPP_StorageTicker;
import LR4.Behaviours.BehavioursForProducer.StorageData.Wind_StorageTicker;
import LR4.INFO.ProducerData;
import LR4.DF_Time_XML.DfHelper;
import LR4.Pro_Topic.Services;
import jade.core.Agent;

import java.util.ArrayList;

public class ProducerAgent extends Agent {
    ProducerData producerData = new ProducerData();

    @Override
    protected void setup() {

        ArrayList<String> AgentsInAllTrade = new ArrayList<>();
        AgentsInAllTrade.add(Services.AgentsInTradeFirst.toString());
        DfHelper.registerItself(AgentsInAllTrade, this);


        addBehaviour(new CreateStorage(this, producerData));


        if (this.getLocalName().equals("SES")) {
            addBehaviour(new SES_StorageTicker(this, 75000, producerData));
        }
        if (this.getLocalName().equals("TPP")) {
            addBehaviour(new TPP_StorageTicker(this, 75000, producerData));
        }
        if (this.getLocalName().equals("Wind")) {
            addBehaviour(new Wind_StorageTicker(this, 75000, producerData));
        }

        addBehaviour(new RecevingFirstMessage(producerData));

        super.setup();
    }
}
