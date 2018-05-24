package aasma.tla.maps;

import aasma.tla.msquare.*;

import java.util.ArrayList;
import java.util.Arrays;

public class BasicExtendedMap extends Map{

    private final static BasicExtendedMap map_instance = new BasicExtendedMap();

    private BasicExtendedMap() {
        Road E = new Road(); //E de estrada, R é para red e green
        Wall W = new Wall();
        TrafficLight T0 = new TrafficLight();
        TrafficLight T1 = new TrafficLight();
        Spawn SN = new Spawn().setCardinalDirection(0);
        Spawn SS = new Spawn().setCardinalDirection(1);
        Spawn SW = new Spawn().setCardinalDirection(2);
        Spawn SE = new Spawn().setCardinalDirection(3);
        Destiny DN = new Destiny().setCardinalDirection(0);
        Destiny DS = new Destiny().setCardinalDirection(1);
        Destiny DW = new Destiny().setCardinalDirection(2);
        Destiny DE = new Destiny().setCardinalDirection(3);
        Crossroad C = new Crossroad();
        this.setMap(new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W,SN,DN,W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,T1,E,E,T0,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(DW,E,E,E,E,E,E,E,E,C,C,E,E,E,E,E,E,E,E,SE)),
                new ArrayList<>(Arrays.asList(SW,E,E,E,E,E,E,E,E,C,C,E,E,E,E,E,E,E,E,DE)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,T0,E,E,T1,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W, E,E, W,W,W,W,W,W,W,W,W)),
                new ArrayList<>(Arrays.asList(W,W,W,W,W,W,W,W,W,DS,SS,W,W,W,W,W,W,W,W,W))
        )));
    }

    public static BasicExtendedMap getInstance() {
        return map_instance;
    }

}
