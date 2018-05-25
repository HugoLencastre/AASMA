package aasma.tla.msquare;

import java.awt.*;
import java.util.HashMap;

public class Destiny extends MapSquare {

    private Coords coords;

    private static HashMap<Integer,VStats> vehicleStats = new HashMap<Integer, VStats>();

    private int cardinalDirection;

    /**
     * 0-North, 1-South, 2-West, 3-East
     * @return
     */
    public Destiny setCardinalDirection(int cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
        return this;
    }

    /**
     * 0-North, 1-South, 2-West, 3-East
     * @return
     */
    public int getCardinalDirection() {
        return cardinalDirection;
    }

    @Override
    public String getStringValue() {
        return "D";
    }

    @Override
    public Color getColorValue() {
        return Color.GRAY;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public void saveVehicleStats(Vehicle v){
        vehicleStats.put(v.getId(),new VStats(v));
    }

    public static int getAverageTimeInMap(){
        int sum = 0;
        for(VStats vs : vehicleStats.values()){
            sum += vs.getTimeInMap();
        }
        return sum/vehicleStats.size();
    }

    public static int getAverageWaitTime(){
        int sum = 0;
        for(VStats vs : vehicleStats.values()){
            sum += vs.getWaitTime();
        }
        return sum/vehicleStats.size();
    }
}
