package LR4.DF_Time_XML;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.ArrayList;
import java.util.List;

public class DfHelper {
    public static List<AID> searchForAgents(String serviceToSearch, Agent ag){
        List<AID> foundAgents = new ArrayList<>();
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd  = new ServiceDescription();
        sd.setType(serviceToSearch);
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(ag, dfd);
            for (DFAgentDescription desc : result) {
                foundAgents.add(desc.getName());
            }
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        return foundAgents;
    }

    public static void registerItself(List<String> serviceName, Agent theAgent){
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(theAgent.getAID());
        for (int i=0; i<serviceName.size(); i++) {
            ServiceDescription sd = new ServiceDescription();
            sd.setType(serviceName.get(i));
            sd.setName(theAgent.getLocalName() + "-" + serviceName.get(i));
            dfd.addServices(sd);
        }
        try {
            DFService.register( theAgent, dfd );
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}
