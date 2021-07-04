package LR4.Behaviours.BehavioursForDistributer;

import LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.BadEE.*;
import LR4.Behaviours.BehavioursForDistributer.DistributerSubBehs.GoodEE.*;
import LR4.INFO.MostMinPriceAfterDivisionData;
import LR4.INFO.NeedsData;
import LR4.INFO.PriceWithNameForDistributerData;
import LR4.INFO.PricesForDistributerData;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;

import java.util.ArrayList;

public class DistributerFSM extends FSMBehaviour {
    private final String
            SENDINGSTART = "Sending_Start",
            RECEIVINGPRICES = "Receiving_Prices",
            CHOSINGBEST = "Chosing_Best",
            WAITINGFORCONFIRM = "Waiting_For_Confirm",
            MINPRICETOOLARGE = "Min_Price_Too_Large",
            BOUGHTENERGY = "I_Bought_Energy",
            BESTALREADYSOLD = "Best_Already_Sold",
            DIVISIONCONTRACT = "Division_Contract",
            RECEIVINGPRICESAFTERDIVISION = "Receiving_Prices_After_Division",
            MINPRICESAFTERDIVISION = "Min_Prices_After_Division",
            MINPRICESTOOLARGEAFTERDIVISION = "Min_Prices_Too_Large_After_Division",
            BOUGHTENERGYAFTERDIVISION = "Bought_Energy_After_Division",
            THEYDONTHAVEENERGY = "They_Dont_Have_Energy",
            WAITINGFORCONFIRMAFTERDIVISION = "Waiting_For_Confirm_After_Division";

    NeedsData needsData;

    PricesForDistributerData pricesForDistributerData = new PricesForDistributerData();
    PriceWithNameForDistributerData bestPrice = new PriceWithNameForDistributerData(0.0, null);
    ArrayList<String> confirmedProducersAfterDivision = new ArrayList<>(); //поменять на дату
    PricesForDistributerData pricesForDistributerDataAfterDivision = new PricesForDistributerData();
    MostMinPriceAfterDivisionData mostMinPriceAfterDivision = new MostMinPriceAfterDivisionData();

    public DistributerFSM(Agent a, NeedsData needsData) {
        super(a);
        this.needsData = needsData;

        registerFirstState(new StartTopicMsg(getAgent(), needsData), SENDINGSTART);
        registerState(new SavePricesParallel(getAgent(), pricesForDistributerData, 10000), RECEIVINGPRICES);
        registerState(new BestPrice(getAgent(), pricesForDistributerData, bestPrice, needsData), CHOSINGBEST);
        registerState(new WaitingForConfirmParallel(getAgent(), 3000), WAITINGFORCONFIRM);
        registerLastState(new BadPricePokypatel(getAgent(), bestPrice), MINPRICETOOLARGE);
        registerLastState(new NeedBadEE_Message(), BOUGHTENERGY);
        registerLastState(new SellerAlreadySold(getAgent(), needsData), BESTALREADYSOLD);

        registerState(new DivisionOneShot(getAgent(), needsData), DIVISIONCONTRACT);
        registerState(new ReceivingPricesAfterDivisionPar(getAgent(), pricesForDistributerDataAfterDivision, 10000), RECEIVINGPRICESAFTERDIVISION);
        registerState(new MinPricesAfterDivisionOneShotBeh(getAgent(), pricesForDistributerDataAfterDivision,mostMinPriceAfterDivision, needsData), MINPRICESAFTERDIVISION);
        registerState(new WaitingForConfirmAfterDivisionParallel(getAgent(),3000, confirmedProducersAfterDivision), WAITINGFORCONFIRMAFTERDIVISION);
        registerLastState(new MinPriceTooLargeAfterDivisionOneShotBeh(getAgent(), mostMinPriceAfterDivision), MINPRICESTOOLARGEAFTERDIVISION);
        registerLastState(new IBoughtEnergyAfterDivisionOneShot(getAgent(), confirmedProducersAfterDivision, needsData), BOUGHTENERGYAFTERDIVISION);
        registerLastState(new TheyDontHaveEnergyOneShotBeh(), THEYDONTHAVEENERGY);

        registerDefaultTransition(SENDINGSTART, RECEIVINGPRICES);
        registerTransition(RECEIVINGPRICES, CHOSINGBEST, 1);
        registerTransition(CHOSINGBEST, MINPRICETOOLARGE, 2);
        registerTransition(CHOSINGBEST, WAITINGFORCONFIRM, 1);
        registerTransition(WAITINGFORCONFIRM, BOUGHTENERGY, 1);
        registerTransition(WAITINGFORCONFIRM, BESTALREADYSOLD, 2);
        registerTransition(RECEIVINGPRICES, DIVISIONCONTRACT, 2);
        registerDefaultTransition(DIVISIONCONTRACT, RECEIVINGPRICESAFTERDIVISION);
        registerTransition(RECEIVINGPRICESAFTERDIVISION, THEYDONTHAVEENERGY, 2);
        registerTransition(RECEIVINGPRICESAFTERDIVISION, MINPRICESAFTERDIVISION, 1);
        registerTransition(MINPRICESAFTERDIVISION, MINPRICESTOOLARGEAFTERDIVISION, 2);
        registerTransition(MINPRICESAFTERDIVISION, WAITINGFORCONFIRMAFTERDIVISION, 1);
        registerTransition(WAITINGFORCONFIRMAFTERDIVISION, BOUGHTENERGYAFTERDIVISION, 1);
        registerTransition(WAITINGFORCONFIRMAFTERDIVISION, THEYDONTHAVEENERGY, 2);

    }
}
