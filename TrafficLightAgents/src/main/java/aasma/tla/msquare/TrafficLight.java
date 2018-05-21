package aasma.tla.msquare;

import aasma.tla.maps.Map;

import java.awt.*;
import java.util.ArrayList;

public class TrafficLight extends MapSquare{

    private boolean isRed = true;

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

    public void toggle() {
        isRed = isRed?false:true;
    }

    TrafficLight setGreen() {
        isRed = false;
        return this;
    }
}
