package LR4.Configs;

import LR4.DF_Time_XML.XmlHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class WindConfigTest {

    @Test
    public void testCreateXml() {

        WindConfig windConfig = new WindConfig();
        ArrayList<Double> production = new ArrayList<>();
        production.add(21.0);
        production.add(13.0);
        production.add(0.0);
        production.add(5.0);
        production.add(15.0);
        production.add(0.0);
        production.add(0.0);
        production.add(17.0);
        production.add(1.0);
        production.add(11.0);
        production.add(4.0);
        production.add(13.0);
        production.add(7.0);
        production.add(0.0);
        production.add(1.0);
        production.add(19.0);
        production.add(5.0);
        production.add(21.0);
        production.add(16.0);
        production.add(18.0);
        production.add(16.0);
        production.add(21.0);
        production.add(17.0);
        production.add(5.0);
        windConfig.setProduction(production);
        XmlHelper.marshalAny(windConfig, "Wind.xml");
    }

}