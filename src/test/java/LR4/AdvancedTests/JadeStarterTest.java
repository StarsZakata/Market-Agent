package LR4.AdvancedTests;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

import java.util.Collections;
import java.util.List;

public abstract class JadeStarterTest {
    private AgentContainer mainContainer;
    public void StartJade() {
        StartJade(Collections.emptyList());
    }
    public void StartJade(List<String> services) {
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter("gui", "true");
        StringBuilder sb = new StringBuilder();
        services.forEach(el -> sb.append(el).append(";"));
        profile.setParameter("services", sb.toString());
        Runtime.instance().setCloseVM(true);
        mainContainer = Runtime.instance().createMainContainer(profile);
    }
    public void CreateAgent(String name, Behaviour... bhs) {
        try {
            AgentController newAgent = mainContainer.createNewAgent(name, TestAgent.class.getName(), bhs);
            newAgent.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
