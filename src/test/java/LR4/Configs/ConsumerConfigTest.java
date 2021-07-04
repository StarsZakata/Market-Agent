package LR4.Configs;

import LR4.DF_Time_XML.XmlHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ConsumerConfigTest {

    @Test
    public void TestCreateXML() {

        ConsumerConfig firstConsumerConfig = new ConsumerConfig();
        List<Double> kWhPerHour = new ArrayList<>();
        kWhPerHour.add(0.95*23.5);
        kWhPerHour.add(0.9*23.5);
        kWhPerHour.add(0.9*23.5);
        kWhPerHour.add(0.9*23.5);
        kWhPerHour.add(0.95*23.5);
        kWhPerHour.add(0.95*23.5);
        kWhPerHour.add(0.95*23.5);
        kWhPerHour.add(1*23.5);
        kWhPerHour.add(1*23.5);
        kWhPerHour.add(1*23.5);
        kWhPerHour.add(0.75*23.5);
        kWhPerHour.add(0.75*23.5);
        kWhPerHour.add(0.75*23.5);
        kWhPerHour.add(0.75*23.5);
        kWhPerHour.add(0.9*23.5);
        kWhPerHour.add(0.9*23.5);
        kWhPerHour.add(1*23.5);
        kWhPerHour.add(1*23.5);
        kWhPerHour.add(0.8*23.5);
        kWhPerHour.add(0.8*23.5);
        kWhPerHour.add(0.8*23.5);
        kWhPerHour.add(0.9*23.5);
        kWhPerHour.add(0.9*23.5);
        firstConsumerConfig.setkWhPerHour(kWhPerHour);
        firstConsumerConfig.setDistributersName("FoodDistributer");
        XmlHelper.marshalAny(firstConsumerConfig, "Food.xml");

        ConsumerConfig secondConsumerConfig = new ConsumerConfig();
        List<Double> secondkWhPerHour = new ArrayList<>();
        secondkWhPerHour.add(0.85*23.5);
        secondkWhPerHour.add(0.85*23.5);
        secondkWhPerHour.add(0.85*23.5);
        secondkWhPerHour.add(0.85*23.5);
        secondkWhPerHour.add(0.80*23.5);
        secondkWhPerHour.add(0.80*23.5);
        secondkWhPerHour.add(0.80*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.100*23.5);
        secondkWhPerHour.add(0.100*23.5);
        secondkWhPerHour.add(0.100*23.5);
        secondkWhPerHour.add(0.95*23.5);
        secondkWhPerHour.add(0.95*23.5);
        secondkWhPerHour.add(0.95*23.5);
        secondkWhPerHour.add(0.95*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondkWhPerHour.add(0.90*23.5);
        secondConsumerConfig.setkWhPerHour(secondkWhPerHour);
        secondConsumerConfig.setDistributersName("ChemiesDistributer");
        XmlHelper.marshalAny(secondConsumerConfig, "Chemies.xml");


        ConsumerConfig thirdConsumerConfig = new ConsumerConfig();
        List<Double> thirdkWhPerHour = new ArrayList<>();
        thirdkWhPerHour.add(0.50*23.5);
        thirdkWhPerHour.add(0.50*23.5);
        thirdkWhPerHour.add(0.20*23.5);
        thirdkWhPerHour.add(0.20*23.5);
        thirdkWhPerHour.add(0.40*23.5);
        thirdkWhPerHour.add(0.40*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.100*23.5);
        thirdkWhPerHour.add(0.100*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.80*23.5);
        thirdkWhPerHour.add(0.100*23.5);
        thirdkWhPerHour.add(0.100*23.5);
        thirdkWhPerHour.add(0.90*23.5);
        thirdkWhPerHour.add(0.90*23.5);
        thirdkWhPerHour.add(0.70*23.5);
        thirdkWhPerHour.add(0.70*23.5);
        thirdkWhPerHour.add(0.70*23.5);
        thirdConsumerConfig.setkWhPerHour(thirdkWhPerHour);
        thirdConsumerConfig.setDistributersName("TransportDistributer");
        XmlHelper.marshalAny(thirdConsumerConfig, "Transport.xml");

    }





}