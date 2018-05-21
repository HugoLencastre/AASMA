package aasma.tla.msquare;

import aasma.tla.agents.Agent;
import aasma.tla.maps.Map;

import java.awt.*;

public class Crossroad extends MapSquare {

    private Coords coords;
    private TrafficLight tl1, tl2;
    private final int carsCountedInCross = 3;
    int vDirNrV = 0;
    int hDirNrV = 0;
    boolean verticalIsGreen = false;
    private Agent agent;

    @Override
    public String getStringValue() {
        return "C";
    }

    @Override
    public Color getColorValue() {
        return Color.yellow;
    }

    public void setData(Map map, Coords coords) {
        this.coords = coords;
        int x = coords.getX();
        int y = coords.getY();
        tl1 = ((TrafficLight) map.getMapSquare(x-1,y-1));
        tl2 = ((TrafficLight) map.getMapSquare(x+2,y-1)).setGreen();
    }

    public Coords getCoordsAfterCross(int dir){
        switch (dir){
            case 0:
                return new Coords(coords.getX()+1, coords.getY()-1);
            case 1:
                return new Coords(coords.getX(), coords.getY()+2);
            case 2:
                return new Coords(coords.getX()-1, coords.getY());
            case 3:
                return new Coords(coords.getX()+2, coords.getY()+1);
        }
        return null;
    }

    public int getNrOfVehiclesInCross(Map map, boolean verticalDir){
        int nrCars = 0;
        Coords cn = getCoordsAfterCross(0);
        Coords cs = getCoordsAfterCross(1);
        Coords cw = getCoordsAfterCross(2);
        Coords ce = getCoordsAfterCross(3);
        if (verticalDir) {
            for (int x = 0; x < this.carsCountedInCross; x++) {
                if (map.getMapSquare(cn.getX()-1,cn.getY()-x) instanceof Vehicle ||
                        map.getMapSquare(cs.getX()+1,cs.getY()+x) instanceof Vehicle){
                    nrCars++;
                }
            }
        } else {
            for (int x = 0; x < this.carsCountedInCross; x++) {
                if (map.getMapSquare(cw.getX()-x,cw.getY()+1) instanceof Vehicle ||
                        map.getMapSquare(ce.getX()+x,ce.getY()-1) instanceof Vehicle){
                    nrCars++;
                }
            }
        }
        return nrCars;
    }

    public void doStep(Map map, int stepNr) {
        if (agent == null) this.agent = map.getAgent();
        if (agent.doStep(this, map, stepNr)) toggle();
    }

    private void toggle() {
        tl1.toggle();
        tl2.toggle();
        verticalIsGreen = verticalIsGreen?false:true;
    }

    public Coords getCoords() {
        return coords;
    }

    public int getHDirNrV() {
        return hDirNrV;
    }

    public int getVDirNrV() {
        return vDirNrV;
    }

    public boolean isVerticalGreen() {
        return verticalIsGreen;
    }
}
