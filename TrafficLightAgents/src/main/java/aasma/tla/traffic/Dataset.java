package aasma.tla.traffic;

public abstract class Dataset {
    public abstract int getSpawnCardinalDirection();
    public abstract int getDestinyCardinalDirection();
    public double getProbToSpawnVehicle(){
        return 0.5;
    }
}
