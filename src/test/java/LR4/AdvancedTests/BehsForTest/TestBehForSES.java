package LR4.AdvancedTests.BehsForTest;

import LR4.Behaviours.BehavioursForProducer.StorageData.CreateStorage;
import LR4.Behaviours.BehavioursForProducer.StorageData.SES_StorageTicker;
import LR4.INFO.ProducerData;
import LR4.DF_Time_XML.DfHelper;
import LR4.Pro_Topic.Services;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

import java.util.ArrayList;

public class TestBehForSES extends FSMBehaviour {
    private final String
            REGISTER = "register",
            CREATESTORAGE = "Create_Storage",
            REFILLINGSTORAGE = "REFILLING_Storage";
    ProducerData producerData;

    public TestBehForSES(ProducerData producerData) {
        this.producerData = producerData;

        registerFirstState(new OneShotBehaviour() {
            @Override
            public void action() {
                ArrayList<String> AgentsInAllTrade = new ArrayList<>();
                AgentsInAllTrade.add(Services.AgentsInTradeFirst.toString());
                DfHelper.registerItself(AgentsInAllTrade, getAgent());
            }
        }, REGISTER);
        registerState(new CreateStorage(getAgent(), producerData), CREATESTORAGE);
        registerLastState(new SES_StorageTicker(getAgent(), 75000, producerData), REFILLINGSTORAGE);

        registerDefaultTransition(REGISTER, CREATESTORAGE);
        registerDefaultTransition(CREATESTORAGE, REFILLINGSTORAGE);
    }
}
