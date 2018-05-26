package aasma.tla.agents;

import aasma.tla.maps.Map;
import aasma.tla.msquare.Crossroad;
import com.github.chen0040.rl.learning.qlearn.QAgent;

import java.util.ArrayList;
import java.util.HashMap;

// epsilon greedy action selection
public class QLearningFourAgents extends Agent {
    private final static HashMap<Crossroad,QAgent> agentMap = new HashMap<>();
    private final static HashMap<Crossroad,Integer> lastActionMap = new HashMap<>();
    //TODO this is hardcoded, need to change
    int stateCount = 1600;
    int actionCount = 2;
    // this max is equal in 5 or 9 squares counted
    int maxNrOfCars = 18;

    @Override
    public boolean doStep(Crossroad cs, Map map, int stepNr) {
        cs.saveDirWithMostPassesAndReset();
        QAgent a;
        int nrHV = cs.getHDirNrV(map);
        int nrVV = cs.getVDirNrV(map);
        if (!agentMap.containsKey(cs)) {
            a = new QAgent(stateCount, actionCount);
            a.start(getState(cs, nrHV, nrVV));
            agentMap.put(cs,a);
            lastActionMap.put(cs,0);
//            System.out.println("Agent starts with action-"+0);
            return false;
        } else {
            a = agentMap.get(cs);
        }

        int newStateId = getState(cs, nrHV, nrVV);
        double reward = getReward(cs, map, nrHV, nrVV);

//        System.out.println("Agent receives Reward = "+reward);
//        System.out.println("Next state is " + newStateId);
//        System.out.println();

        a.update(lastActionMap.get(cs), newStateId, reward);

        int actionId = a.selectAction().getIndex();
        lastActionMap.replace(cs, actionId);

//        System.out.println("The state is " + newStateId);
//        System.out.println("Agent does action-"+actionId);

        return actionId != 0;
    }

    private int getState(Crossroad cs, int nrHV, int nrVV) {
//        return (nrHV+1)+(nrVV+1)*10+(cs.isVerticalGreen()?1:2)*100;
        ArrayList<Crossroad> csNearList = cs.getCsNearList();
        return (nrHV)+(nrVV)*10+csNearList.get(0).getDirMostPasses()*100+csNearList.get(1).getDirMostPasses()*1000;
    }

    private int getReward(Crossroad cs, Map map, int nrHV, int nrVV) {
        int vehiclesWaitingReward = (maxNrOfCars-nrHV-nrVV);
        int vehiclesPassedReward = cs.getNrVehiclesPassedAndReset();
        if (map.squaresCountedInSquare == nrHV ||
                map.squaresCountedInSquare == nrVV){
//            System.out.println("Very bad");
            return 0;
        }
//        System.out.println("Reward");
//        System.out.println("vehiclesWaitingReward: "+vehiclesWaitingReward);
//        System.out.println("vehiclesPassedReward: "+vehiclesPassedReward);
        return vehiclesWaitingReward * vehiclesPassedReward;
    }
}
