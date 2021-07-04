package LR4.Configs;

import LR4.DF_Time_XML.XmlHelper;
import org.junit.jupiter.api.Test;

class DistributerConfigTest {

    @Test
    public void TestCreateXML() {

        DistributerConfig firstDistributerConfig = new DistributerConfig();
        firstDistributerConfig.setProducersName("Food");
        XmlHelper.marshalAny(firstDistributerConfig, "FoodDistributer.xml");

        DistributerConfig secondDistributerConfig = new DistributerConfig();
        secondDistributerConfig.setProducersName("Chemies");
        XmlHelper.marshalAny(secondDistributerConfig, "ChemiesDistributer.xml");

        DistributerConfig thirdDistributerConfig = new DistributerConfig();
        thirdDistributerConfig.setProducersName("Transport");
        XmlHelper.marshalAny(thirdDistributerConfig, "TransportDistributer.xml");

    }
}