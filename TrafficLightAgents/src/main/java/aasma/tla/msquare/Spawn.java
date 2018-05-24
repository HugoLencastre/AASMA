package aasma.tla.msquare;

import aasma.tla.TrafficLightAgents;
import aasma.tla.maps.Map;

import java.awt.*;

public class Spawn extends MapSquare{

    private int cardinalDirection;
    static int x = 0;

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
    int timer = 20;
    public void doStep(Map map, Coords c, Coords dest) {
//        if (x > timer && x <= timer*3) {
//            x++;
//            return;
//        } else if (x > timer) {
//            x = 0;
//        }
//        x++;
        int x = c.getX();
        int y = c.getY();
        Coords ca = map.getCoordsAhead(x, y, map.getOppositeDirection(cardinalDirection));
        MapSquare ms = map.getMapSquare(ca);
        if (!TrafficLightAgents.REALISTIC_REACTIONS && ms instanceof Vehicle) {
            ((Vehicle) ms).doStep(map, ca);
            ms = map.getMapSquare(ca);
        }
        if (ms instanceof Road) {
            //prob can delete
            if (TrafficLightAgents.REALISTIC_REACTIONS && ((Road) ms).wasVehicleHereRecently()) {
                ((Road) ms).tap();
                return;
            }
            Vehicle v = new Vehicle(map.getOppositeDirection(cardinalDirection)).setDestiny(dest);
            map.changeMapSquare(v, ca);
            Vehicle.addVehicleToStepped(v);
//            System.out.println("Spawned vehicle in " + cardinalDirection);
        }
    }
}
