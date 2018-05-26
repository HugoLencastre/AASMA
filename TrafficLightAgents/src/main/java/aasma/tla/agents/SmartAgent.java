package aasma.tla.agents;

import aasma.tla.maps.Map;
import aasma.tla.msquare.Crossroad;

public class SmartAgent extends Agent {

    @Override
    public boolean doStep(Crossroad cs, Map map, int stepNr) {
        int vDirNrV = cs.getNrOfVehiclesInCross(map,true);
        int hDirNrV = cs.getNrOfVehiclesInCross(map,false);
//        System.out.println("Nr of vehicles in vertical direction: "+vDirNrV+" (max "+carsCountedInCross+")");
//        System.out.println("Nr of vehicles in horizontal direction: "+hDirNrV+" (max "+carsCountedInCross+")");
        if (vDirNrV >= hDirNrV) {
            return true;
        } else {
            return false;
        }
    }
}
