package aasma.tla.agents;

import aasma.tla.maps.Map;
import aasma.tla.msquare.Crossroad;

public class BasicAgent extends Agent {

    @Override
    public boolean doStep(Crossroad cs, Map map, int stepNr) {
        if (stepNr%60 >= 30) {
            return true;
        }
        return false;
    }
}
