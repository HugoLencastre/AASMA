package aasma.tla.maps;

import aasma.tla.msquare.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoCrossMap extends Map{

    private final static TwoCrossMap map_instance = new TwoCrossMap();

    private TwoCrossMap() {
        Road E = new Road(); //E de estrada, R é para red e green
        Wall W = new Wall();
        TrafficLight T0 = new TrafficLight();
        TrafficLight T1 = new TrafficLight();
        TrafficLight T2 = new TrafficLight();
        TrafficLight T3 = new TrafficLight();
        TrafficLight T4 = new TrafficLight();
        TrafficLight T5 = new TrafficLight();
        TrafficLight T6 = new TrafficLight();
        TrafficLight T7 = new TrafficLight();
        Spawn SN1 = new Spawn().setCardinalDirection(0);
        Spawn SS1 = new Spawn().setCardinalDirection(1);
        Spawn SN2 = new Spawn().setCardinalDirection(0);
        Spawn SS2 = new Spawn().setCardinalDirection(1);
        Spawn SW1 = new Spawn().setCardinalDirection(2);
        Spawn SE1 = new Spawn().setCardinalDirection(3);
        Destiny DN1 = new Destiny().setCardinalDirection(0);
        Destiny DS1 = new Destiny().setCardinalDirection(1);
        Destiny DN2 = new Destiny().setCardinalDirection(0);
        Destiny DS2 = new Destiny().setCardinalDirection(1);
        Destiny DW1 = new Destiny().setCardinalDirection(2);
        Destiny DE1 = new Destiny().setCardinalDirection(3);
        Crossroad C1 = new Crossroad();
        Crossroad C2 = new Crossroad();
        this.setMap(new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,SN1,DN1,W,W,W,W,W,W,SN2,DN2,W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,T3, E,E, T2,W,W,W,W,T4, E,E, T5,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList(DW1,E,E,E,E,E,C1,C1, E,E,E,E,E,E, C2,C2,E,E,E,E,E,SE1)),
                new ArrayList<>(Arrays.asList(SW1,E,E,E,E,E,C1,C1, E,E,E,E,E,E, C2,C2,E,E,E,E,E,DE1)),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,T0, E,E, T1,W,W,W,W,T6, E,E, T7,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,  E,E,  W,W,W,W,W,W,  E,E,  W,W,W,W,W,W )),
                new ArrayList<>(Arrays.asList( W,W,W,W,W,W,DS1,SS1,W,W,W,W,W,W,DS2,SS2,W,W,W,W,W,W ))
        )));
    }

    public static TwoCrossMap getInstance() {
        return map_instance;
    }

}
