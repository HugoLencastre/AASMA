package aasma.tla.msquare;

import java.awt.*;

public class Wall extends MapSquare {

    @Override
    public String getStringValue() {
        return "W";
    }

    @Override
    public Color getColorValue() {
        return Color.WHITE;
    }
}
