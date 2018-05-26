package aasma.tla.agents;

import aasma.tla.maps.Map;
import aasma.tla.msquare.Crossroad;

public class ChangerAgent extends Agent {

    @Override
    public boolean doStep(Crossroad cs, Map map, int stepNr) {
        if (stepNr%20 == 0) {
            return true;
        }
        return false;
    }
}
