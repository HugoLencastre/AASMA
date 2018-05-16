package aasma.tla.msquare;

import aasma.tla.maps.Map;

public class TrafficLight extends MapSquare{

    private boolean isRed = true;

    public boolean isRed() {
        return isRed;
    }

    @Override
    public String getStringValue() {
        return isRed?"R":"G";
    }

    public void doStep(Map map, Coords c) {

    }
}
