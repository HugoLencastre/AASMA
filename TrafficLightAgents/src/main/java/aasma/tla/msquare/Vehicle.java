package aasma.tla.msquare;

import aasma.tla.TrafficLightAgents;
import aasma.tla.maps.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Vehicle extends MapSquare{

    private static ArrayList<Vehicle> vStep = new ArrayList<>();
    private int currentDirection = -1;
    private Coords destiny;

    public Vehicle(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public String getStringValue() {
        return "V";
    }

    @Override
    public Color getColorValue() {
        return Color.BLUE;
    }

    public Vehicle setDestiny(Coords destiny) {
        this.destiny = destiny;
        return this;
    }

    public void doStep(Map map, Coords c) {
        if (vStep.contains(this)) {
            return;
        }
        vStep.add(this);
        if (currentDirection == -1) {
            System.out.println("Error: Direction cannot be -1");
            return;
        }
        Coords ca = map.getCoordsAhead(c, currentDirection);
        MapSquare ms = map.getMapSquare(ca);
        int newDir = -1;
        if (ms instanceof Crossroad){
            if(map.getMyTrafficLight(c, currentDirection).isRed()){
//                System.out.println("Cant go, light is red");
                return;
            }
            int diffX = destiny.getX()-c.getX();
            if(!(diffX > -3 && diffX < 3)){
                if (diffX > 0){
                    newDir = 3;
                } else {
                    newDir = 2;
                }
            }
            int diffY = destiny.getY()-c.getY();
            if(!(diffY > -3 && diffY < 3)){
                if (newDir != -1){
                    if (new Random().nextDouble() < 0.5) {
                        if (diffY > 0){
                            newDir = 1;
                        } else {
                            newDir = 0;
                        }
                    }
                } else {
                    if (diffY > 0){
                        newDir = 1;
                    } else {
                        newDir = 0;
                    }
                }
            }
            ca = ((Crossroad) ms).getCoordsAfterCross(newDir);
//            System.out.println(ca + " after cross");
            ms = map.getMapSquare(ca);
        }
        if (ms instanceof Vehicle) {
            ((Vehicle) ms).doStep(map, ca);
            return;
//            ms = map.getMapSquare(ca);
//            if (ms instanceof Vehicle) {
//                return;
//            }
        }
        if (TrafficLightAgents.REALISTIC_REACTIONS && ms instanceof Road && ((Road) ms).getVehicleHereRecently() != 0) {
            ((Road) ms).tap();
            return;
        }
        if (newDir != -1) {
//            System.out.println("I was going " + currentDirection + " i turned to " + newDir);
            this.currentDirection = newDir;
        }
        if (ms instanceof Destiny) {
//            System.out.println("I arrived at my destiny");
            map.changeMapSquare(new Road(), c);
            return;
        }
        if (TrafficLightAgents.REALISTIC_REACTIONS) {
            int nn=0;
            switch (currentDirection) {
                case 0:
                case 2:
                    nn=2;
                    break;
                case 1:
                case 3:
                    nn=0;
                    break;
            }
            map.changeMapSquare(new Road(nn), c);
        } else {
            map.changeMapSquare(new Road(), c);
        }
        map.changeMapSquare(this, ca);
//        System.out.println("I went from " + c + " to " + ca);
    }

    public static void reset() {
        vStep = new ArrayList<>();
    }

    public static void addVehicleToStepped(Vehicle v){
        vStep.add(v);
    }

    public int getCurrentDirection() {
        return currentDirection;
    }
}
