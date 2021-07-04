package LR4.Configs;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "SESConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class SESConfig {

    @XmlElementWrapper(name = "SESConfig")
    @XmlElement(name = "values")
    ArrayList<Double> configForSES;

    public ArrayList<Double> getConfigForSES() {
        return configForSES;
    }

    public void setConfigForSES(ArrayList<Double> configForSES) {
        this.configForSES = configForSES;
    }
}
