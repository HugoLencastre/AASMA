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
    private int step = 0;
    private int waitStep = 0;
    private static int idCount = 0;
    private int id;

    public Vehicle(int currentDirection) {
        this.currentDirection = currentDirection;
        id = idCount;
        idCount++;
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
        step++;
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
        Crossroad cross = null;
        int newDir = -1;
        if (ms instanceof Crossroad){
            if(map.getMyTrafficLight(c, currentDirection).isRed()){
//                System.out.println("Cant go, light is red");
                waitStep++;
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
            cross = (Crossroad) ms;
            ca = ((Crossroad) ms).getCoordsAfterCross(newDir);
//            System.out.println(ca + " after cross");
            ms = map.getMapSquare(ca);
        }
        if (ms instanceof Vehicle) {
            ((Vehicle) ms).doStep(map, ca);
            if (TrafficLightAgents.REALISTIC_REACTIONS) {
                waitStep++;
                return;
            }
            ms = map.getMapSquare(ca);
            if (ms instanceof Vehicle) {
                waitStep++;
                return;
            }
        }
        if (TrafficLightAgents.REALISTIC_REACTIONS && ms instanceof Road && ((Road) ms).wasVehicleHereRecently()) {
            ((Road) ms).tap();
            waitStep++;
            return;
        }
        if (ms instanceof Destiny) {
//            System.out.println("I arrived at my destiny");
            map.changeMapSquare(new Road(), c);
//            System.out.println("im car with id: "+ id + " and was here for "+step+" steps and waited "+waitStep+" steps");
            ((Destiny) ms).saveVehicleStats(this);
            return;
        }

        if (TrafficLightAgents.REALISTIC_REACTIONS && (currentDirection == 0 || currentDirection == 2)) {
            map.changeMapSquare(new Road().setVHRTrue(), c);
        } else {
            map.changeMapSquare(new Road(), c);
        }
        map.changeMapSquare(this, ca);
//        System.out.println("I went from " + c + " to " + ca);
        if (newDir != -1) {
//            System.out.println("I was going " + currentDirection + " i turned to " + newDir);
            this.currentDirection = newDir;
            cross.addPassedVehicle(newDir);
        }
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

    public int getStep() {
        return step;
    }

    public int getWaitStep() {
        return waitStep;
    }

    public int getId() {
        return id;
    }
}
