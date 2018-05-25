package aasma.tla.traffic;

import java.util.Random;

public class RushHourDataset extends Dataset{

    private final double NORTH_PROB = 0.6;
    private final double SOUTH_PROB = 0.2;
    private final double WEST_PROB = 0.1;
    private final double EAST_PROB = 0.1;
    private final double spawnProb = 1.0;
    private final Random rand = new Random();

    /**
     * 0-North, 1-South, 2-West, 3-East
     * @return
     */
    @Override
    public int getSpawnCardinalDirection() {
        double r = rand.nextDouble();
        if(r<NORTH_PROB){
            return 0;
        }else if((r-=NORTH_PROB)<SOUTH_PROB){
            return 1;
        }else if((r-=SOUTH_PROB)<WEST_PROB){
            return 2;
        }else{
            return 3;
        }
    }

    /**
     * 0-North, 1-South, 2-West, 3-East
     * @return
     */
    @Override
    public int getDestinyCardinalDirection() {
        int dir = getSpawnCardinalDirection() + 1;
        return dir==4?0:dir;
    }

    @Override
    public double getProbToSpawnVehicle() {
        return spawnProb;
    }
}
