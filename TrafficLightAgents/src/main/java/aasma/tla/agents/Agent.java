package aasma.tla.agents;

import aasma.tla.maps.Map;
import aasma.tla.msquare.Crossroad;

public abstract class Agent {
    abstract public boolean doStep(Crossroad cs, Map map, int stepNr);
}

