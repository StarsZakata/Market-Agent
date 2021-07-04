package LR4.Configs;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "natureOfTheLoad")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsumerConfig {

    @XmlElementWrapper(name = "Load")
    @XmlElement(name = "kWhPerHour")
    private List<Double> kWhPerHour;

    public String getDistributersName() {
        return distributersName;
    }
    public void setDistributersName(String distributersName) {
        this.distributersName = distributersName;
    }

    @XmlElement(name = "DistributersName")
    private String distributersName;
    public List<Double> getkWhPerHour() {
        return kWhPerHour;
    }
    public void setkWhPerHour(List<Double> kWhPerHour) {
        this.kWhPerHour = kWhPerHour;
    }
}
