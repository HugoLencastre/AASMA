package aasma.tla.agents;

import aasma.tla.maps.Map;
import aasma.tla.msquare.Crossroad;
import com.github.chen0040.rl.learning.qlearn.QAgent;

import java.util.HashMap;

// epsilon greedy action selection
public class QLearningAgent extends Agent {
    private final static HashMap<Crossroad,QAgent> agentMap = new HashMap<>();
    private final static HashMap<Crossroad,Integer> lastActionMap = new HashMap<>();
    //TODO this is hardcoded, need to change
    int stateCount = 32;
    int actionCount = 2;
    int maxNrOfCars = 12;

    @Override
    public boolean doStep(Crossroad cs, Map map, int stepNr) {
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
        double reward = getReward(nrHV, nrVV);

//        System.out.println("Now the new state is " + newStateId);
//        System.out.println("Agent receives Reward = "+reward);

        a.update(lastActionMap.get(cs), newStateId, reward);

        int actionId = a.selectAction().getIndex();
        lastActionMap.replace(cs, actionId);

//        System.out.println("Agent does action-"+actionId);

        return actionId==0?false:true;
    }

    private int getState(Crossroad cs, int nrHV, int nrVV) {
        return (nrHV+1)+(nrVV+1)*10+(cs.isVerticalGreen()?1:2)*100;
    }

    private int getReward(int nrHV, int nrVV) {
        return (maxNrOfCars-nrHV-nrVV)/maxNrOfCars;
    }
}
