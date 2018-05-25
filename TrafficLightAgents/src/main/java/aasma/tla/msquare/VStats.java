package aasma.tla.msquare;

public class VStats {

    private int timeInMap;
    private int waitTime;

    public VStats(Vehicle v) {
        timeInMap = v.getStep();
        waitTime = v.getWaitStep();
    }

    public int getTimeInMap() {
        return timeInMap;
    }

    public int getWaitTime() {
        return waitTime;
    }
}
