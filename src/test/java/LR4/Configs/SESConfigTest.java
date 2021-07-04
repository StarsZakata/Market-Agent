package LR4.Configs;

import LR4.DF_Time_XML.XmlHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class SESConfigTest {

    @Test
    public void TestCreateXML() {

        SESConfig sesConfig = new SESConfig();

        ArrayList<Double> arrayListForSes = new ArrayList<>();
        arrayListForSes.add(-86.884);
        arrayListForSes.add(22.344);
        arrayListForSes.add(-1.450);
        arrayListForSes.add(0.027);
        sesConfig.setConfigForSES(arrayListForSes);
        XmlHelper.marshalAny(sesConfig, "SES.xml");
    }

}