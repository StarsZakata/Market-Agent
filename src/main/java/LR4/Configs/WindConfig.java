package LR4.Configs;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "WindConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class WindConfig {

    @XmlElementWrapper(name = "Production")
    @XmlElement(name = "product")
    ArrayList<Double> production;

    public ArrayList<Double> getProduction() {
        return production;
    }

    public void setProduction(ArrayList<Double> production) {
        this.production = production;
    }
}
