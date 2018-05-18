package aasma.tla.msquare;

import aasma.tla.maps.Map;

import java.awt.*;

public class TrafficLight extends MapSquare{

    private boolean isRed = false;
    private Coords coords;

    public boolean isRed() {
        return isRed;
    }

    @Override
    public String getStringValue() {
        return isRed?"R":"G";
    }

    @Override
    public Color getColorValue() {
        return isRed?Color.RED:Color.GREEN;
    }

    public TrafficLight setCoords(Coords coords) {
        this.coords = coords;
        return this;
    }

    public void doStep(Map map, int stepNr) {
        if (stepNr%15 == 0) {
            toggle();
        }
    }

    public void toggle() {
        isRed = isRed?false:true;
    }
}
