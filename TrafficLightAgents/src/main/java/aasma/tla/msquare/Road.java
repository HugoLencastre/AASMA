package aasma.tla.msquare;

import java.awt.*;

public class Road extends MapSquare {

    private boolean vehicleHereRecently = false;

    public void tap() {
        if (vehicleHereRecently) {
            vehicleHereRecently = false;
        }
    }

    public Road setVHRTrue() {
        this.vehicleHereRecently = true;
        return this;
    }

    public boolean wasVehicleHereRecently() {
        return vehicleHereRecently;
    }

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
