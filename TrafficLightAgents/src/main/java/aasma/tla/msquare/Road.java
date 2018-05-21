package aasma.tla.msquare;

import java.awt.*;

public class Road extends MapSquare {

    private int vehicleHereRecently;

    public Road() {
        vehicleHereRecently = 0;
    }

    public Road(int vhr) {
        vehicleHereRecently = vhr;
    }

    public void tap() {
        if (vehicleHereRecently > 0) vehicleHereRecently -= 1;
    }

    public int getVehicleHereRecently() {
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
