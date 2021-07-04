package LR4.AdvancedTests;

import LR4.AdvancedTests.BehsForTest.TestForConsumer;
import LR4.AdvancedTests.BehsForTest.TestBehForSES;
import LR4.AdvancedTests.BehsForTest.TestBehForTPP;
import LR4.AdvancedTests.BehsForTest.TestBehForWind;
import LR4.Behaviours.BehavioursForDistributer.DistributerBehaviour;
import LR4.Behaviours.BehavioursForProducer.RecevingFirstMessage;
import LR4.INFO.ConsumerData;
import LR4.INFO.ProducerData;                      //свидетельство о войне
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MyFSMTest extends JadeStarterTest {
    ProducerData producerData = new ProducerData();
    ConsumerData consumerData = new ConsumerData();

    @Test
    public void fsmTest1() throws InterruptedException { //тесты нужно запускать по отдельности

        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");

        StartJade(services);

        TestForConsumer behaviourToTest = new TestForConsumer(consumerData, 13, 20);

        CreateAgent("FoodDistributer", new DistributerBehaviour());
        CreateAgent("SES", new TestBehForSES(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("TPP", new TestBehForTPP(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("Wind", new TestBehForWind(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("Food", behaviourToTest);

        Thread.sleep(100000);

        Assertions.assertEquals(2,behaviourToTest.onEnd()); //2 - это цена слишком велика.
    }

    @Test
    public void fsmTest2() throws InterruptedException {

        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");

        StartJade(services);

        TestForConsumer behaviourToTest = new TestForConsumer(consumerData, 10, 50);

        CreateAgent("FoodDistributer", new DistributerBehaviour());
        CreateAgent("SES", new TestBehForSES(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("TPP", new TestBehForTPP(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("Wind", new TestBehForWind(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("Food", behaviourToTest);

        Thread.sleep(100000);

        Assertions.assertEquals(1,behaviourToTest.onEnd()); //1 - это покупка энергии по нормальному контракту

    }

    @Test
    public void fsmTest3() throws InterruptedException {

        List<String> services = new ArrayList<>();
        services.add("jade.core.messaging.TopicManagementService");
        services.add("jade.core.event.NotificationService");

        StartJade(services);

        TestForConsumer behaviourToTest = new TestForConsumer(consumerData, 25, 50);

        CreateAgent("FoodDistributer", new DistributerBehaviour());
        CreateAgent("SES", new TestBehForSES(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("TPP", new TestBehForTPP(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("Wind", new TestBehForWind(producerData), new RecevingFirstMessage(producerData));
        CreateAgent("Food", behaviourToTest);

        Thread.sleep(100000);

        Assertions.assertEquals(4,behaviourToTest.onEnd()); //4 - это покупка энергии по разделённому контракту
    }

}
