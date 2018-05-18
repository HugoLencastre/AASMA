package aasma.tla.msquare;

import java.awt.*;

public class Road extends MapSquare {

    //im creating new roads on vehicle
    @Override
    public String getStringValue() {
        return "E";
    }

    @Override
    public Color getColorValue() {
        return Color.BLACK;
    }
}
