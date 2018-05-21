package aasma.tla.msquare;

import aasma.tla.TrafficLightAgents;
import aasma.tla.maps.Map;

import java.awt.*;

public class Spawn extends MapSquare{

    private int cardinalDirection;

    @Override
    public String getStringValue() {
        return "S";
    }

    @Override
    public Color getColorValue() {
        return Color.DARK_GRAY;
    }

    /**
     * 0-North, 1-South, 2-West, 3-East
     *
     * @return
     */
    public Spawn setCardinalDirection(int cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
        return this;
    }

    /**
     * 0-North, 1-South, 2-West, 3-East
     *
     * @return
     */
    public int getCardinalDirection() {
        return cardinalDirection;
    }

    //will not spawn if car already exists
    public void doStep(Map map, Coords c, Coords dest) {
        int x = c.getX();
        int y = c.getY();
        Coords ca = map.getCoordsAhead(x, y, map.getOppositeDirection(cardinalDirection));
        MapSquare ms = map.getMapSquare(ca);
        if (ms instanceof Road) {
            if (TrafficLightAgents.REALISTIC_REACTIONS && ((Road) ms).wasVehicleHereRecently()) {
                ((Road) ms).tap();
                return;
            }
            Vehicle v = new Vehicle(map.getOppositeDirection(cardinalDirection)).setDestiny(dest);
            map.changeMapSquare(v, ca);
            Vehicle.addVehicleToStepped(v);
//            System.out.println("Spawned vehicle in " + cardinalDirection);
        }
        // missing logistic for REALISTIC_REACTIONS = false and a car being after spawn in north and west
    }
}
