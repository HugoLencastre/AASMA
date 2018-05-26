package aasma.tla.msquare;

import aasma.tla.TrafficLightAgents;
import aasma.tla.agents.Agent;
import aasma.tla.maps.Map;

import java.awt.*;
import java.util.ArrayList;

public class Crossroad extends MapSquare {

    private Coords coords;
    private TrafficLight tl1, tl2;
//    private final int squaresCountedInCross = 10;
    private boolean verticalIsGreen = false;
    private Agent agent;

    private int maxTime = TrafficLightAgents.TL_MAX_TIMER;
    private int minTime = TrafficLightAgents.TL_MIN_TIMER;
    private int lastToggle = 0;

    private int nrVeciclesPassed = 0;

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
        int squaresCountedInCross = map.squaresCountedInSquare;
        if (verticalDir) {
            for (int x = 0; x < squaresCountedInCross; x++) {
//                if (map.getMapSquare(cn.getX()-1,cn.getY()-x) instanceof Vehicle ||
//                        map.getMapSquare(cs.getX()+1,cs.getY()+x) instanceof Vehicle){
//                    nrCars++;
//                }
                if (map.getMapSquare(cn.getX()-1,cn.getY()-x) instanceof Vehicle){
                    nrCars++;
                }
                if (map.getMapSquare(cs.getX()+1,cs.getY()+x) instanceof Vehicle){
                    nrCars++;
                }
                if (nrCars > 9){
                    return 9;
                }
            }
        } else {
            for (int x = 0; x < squaresCountedInCross; x++) {
//                if (map.getMapSquare(cw.getX()-x,cw.getY()+1) instanceof Vehicle ||
//                        map.getMapSquare(ce.getX()+x,ce.getY()-1) instanceof Vehicle){
//                    nrCars++;
//                }
                if (map.getMapSquare(cw.getX()-x,cw.getY()+1) instanceof Vehicle){
                    nrCars++;
                }
                if (map.getMapSquare(ce.getX()+x,ce.getY()-1) instanceof Vehicle){
                    nrCars++;
                }
                if (nrCars > 9){
                    return 9;
                }
            }
        }
        return nrCars;
    }

    public void doStep(Map map, int stepNr) {
        if (agent == null) this.agent = map.getAgent();
        if (TrafficLightAgents.TLTIMER) {
            int stepDiff = stepNr - lastToggle;
            if (stepDiff < minTime) return;
            if (stepDiff > maxTime) {
                toggle();
                return;
            }
        }
        //this is here to slow down step
        this.lastToggle = stepNr;
        boolean changeToVerticalGreen = agent.doStep(this, map, stepNr);
        if (changeToVerticalGreen && !verticalIsGreen) {
            toggle();
        } else if (!changeToVerticalGreen && verticalIsGreen) {
            toggle();
        }
    }

    private void toggle() {
//        this.lastToggle = stepNr;
        tl1.toggle();
        tl2.toggle();
        verticalIsGreen = !verticalIsGreen;
    }

    public Coords getCoords() {
        return coords;
    }

    public int getHDirNrV(Map map) {
        return getNrOfVehiclesInCross(map, false);
    }

    public int getVDirNrV(Map map) {
        return getNrOfVehiclesInCross(map, true);
    }

    public boolean isVerticalGreen() {
        return verticalIsGreen;
    }

    int vn = 0;
    int vs = 0;
    int vw = 0;
    int ve = 0;
    public void addPassedVehicle(int dir) {
        nrVeciclesPassed++;
        switch (dir) {
            case 0:
                vn++;
                break;
            case 1:
                vs++;
                break;
            case 2:
                vw++;
                break;
            case 3:
                ve++;
                break;

        }
    }

    public int getNrVehiclesPassedAndReset() {
        int x = nrVeciclesPassed;
        nrVeciclesPassed = 0;
        return x;
    }

    private int dirMostPasses = 0;
    public void saveDirWithMostPassesAndReset() {
       int dirMostPasses = vn>vs?(vn>vw?(vn>ve?0:3):(vw>ve?2:3)):(vs>vw?(vs>ve?1:3):(vw>ve?2:3));
       vn = 0;
       vs = 0;
       vw = 0;
       ve = 0;
    }

    public int getDirMostPasses() {
        return dirMostPasses;
    }

    private ArrayList<Crossroad> csNearList = new ArrayList<>();
    public void addNearCross (Crossroad cs) {
        csNearList.add(cs);
    }

    public ArrayList<Crossroad> getCsNearList() {
        return csNearList;
    }
}
