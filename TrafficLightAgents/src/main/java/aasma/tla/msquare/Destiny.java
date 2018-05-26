package aasma.tla.msquare;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.awt.*;
import java.util.HashMap;

public class Destiny extends MapSquare {

    private Coords coords;

    private static HashMap<Integer,VStats> vehicleStats = new HashMap<Integer, VStats>();
    private static CircularFifoQueue<VStats> last5VS = new CircularFifoQueue<>(20);

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
        VStats vs = new VStats(v);
        vehicleStats.put(v.getId(),vs);
        last5VS.add(vs);
    }

    public static double getAverageTimeInMap(){
        if (vehicleStats.size() == 0) return 0;
        double sum = 0;
        for(VStats vs : vehicleStats.values()){
            sum += vs.getTimeInMap();
        }
        return sum/vehicleStats.size();
    }

    public static double getAverageWaitTime(){
        if (vehicleStats.size() == 0) return 0;
        double sum = 0;
        for(VStats vs : vehicleStats.values()){
            sum += vs.getWaitTime();
        }
        return sum/vehicleStats.size();
    }

    public static double getATMLastVehicles() {
        if (last5VS.size() != last5VS.maxSize()) return 0;
        double sum = 0;
        for(VStats vs : last5VS){
            sum += vs.getTimeInMap();
        }
        return sum/5;
    }

    public static double getAWTLastVehicles() {
        if (last5VS.size() != last5VS.maxSize()) return 0;
        double sum = 0;
        for(VStats vs : last5VS){
            sum += vs.getWaitTime();
        }
        return sum/5;
    }
}
