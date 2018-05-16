package aasma.tla.msquare;

import aasma.tla.maps.Map;

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
            System.out.println(ca + " after cross");
            ms = map.getMapSquare(ca);
        }
        if (ms instanceof Vehicle) {
            ((Vehicle) ms).doStep(map, ca);
            ms = map.getMapSquare(ca);
            if (ms instanceof Vehicle) {
                return;
            }
        }
        if (newDir != -1) {
            System.out.println("I was going " + currentDirection + " i turned to " + newDir);
            this.currentDirection = newDir;
        }
        map.changeMapSquare(new Road(), c);
        map.changeMapSquare(this, ca);
        System.out.println("I went from " + c + " to " + ca);
    }

    public static void reset() {
        vStep = new ArrayList<>();
    }

    public static void addVehicleToSteped(Vehicle v){
        vStep.add(v);
    }
}
