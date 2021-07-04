package LR4.Configs;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DistributerConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class DistributerConfig {

    @XmlElement(name = "PokypatelName")
    private String ProducersName;

    public String getProducersName() {
        return ProducersName;
    }

    public void setProducersName(String producersName) {
        ProducersName = producersName;
    }
}
