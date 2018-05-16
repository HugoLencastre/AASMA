package aasma.tla.maps;

import aasma.tla.msquare.*;
import aasma.tla.traffic.Dataset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public abstract class Map {

    private ArrayList<ArrayList<MapSquare>> map;
    private Dataset traffic;
    private ArrayList<TrafficLight> tls = new ArrayList<>();
    private HashMap<Integer, ArrayList<Spawn>> spawns = new HashMap<>();
    private HashMap<Integer, ArrayList<Destiny>> destinies = new HashMap<>();
    private Random r = new Random();
    private final double spawnProb = 0.5;

    public void changeMapSquare(MapSquare ms, int x, int y) {
        ArrayList<MapSquare> line = map.get(y);
        line.remove(x);
        line.add(x, ms);
    }

    public void changeMapSquare(MapSquare ms, Coords c) {
        changeMapSquare(ms, c.getX(), c.getY());
    }

    public MapSquare getMapSquare(int x, int y) {
        return map.get(y).get(x);
    }

    public MapSquare getMapSquare(Coords c) {
        return getMapSquare(c.getX(), c.getY());
    }

    public void doMapStep(boolean verbose) {
        if (traffic == null) {
            System.out.println("No Dataset");
            return;
        }
        Spawn spawnToSpawn = null;
        Destiny destinyToVehicle = null;
        if (r.nextDouble() < spawnProb) {
            int spawnDir = traffic.getSpawnCardinalDirection();
            ArrayList<Spawn> spawnsInDir = spawns.get(spawnDir);
            spawnToSpawn = spawnsInDir.get(r.nextInt(spawnsInDir.size()));

            int destinyDir = traffic.getDestinyCardinalDirection();
            while (destinyDir == spawnDir){
                destinyDir = traffic.getDestinyCardinalDirection();
            }
            ArrayList<Destiny> destiniesInDir = destinies.get(destinyDir);
            int ri = r.nextInt(destiniesInDir.size());
            destinyToVehicle = destiniesInDir.get(ri);
        }
        for (int y = 0; y < map.size(); y++) {
            ArrayList<MapSquare> line = map.get(y);
            for (int x = 0; x < line.size(); x++) {
                MapSquare ms = line.get(x);
                if (ms instanceof TrafficLight) {
                    ((TrafficLight) ms).doStep(this, new Coords(x, y));
                } else if (ms instanceof Vehicle) {
                    ((Vehicle) ms).doStep(this, new Coords(x, y));
                } else if (ms == spawnToSpawn) {
                    ((Spawn) ms).doStep(this, new Coords(x, y), destinyToVehicle.getCoords());
                }
            }
        }
        Vehicle.reset();
        if (verbose) {
            printMap();
        }
    }

    public int getOppositeDirection(int dir) {
        return dir < 2 ? (dir == 0 ? 1 : 0) : (dir == 2 ? 3 : 2);
    }

    void setMap(ArrayList<ArrayList<MapSquare>> map) {
        this.map = map;
        spawns.put(0, new ArrayList<>());
        spawns.put(1, new ArrayList<>());
        spawns.put(2, new ArrayList<>());
        spawns.put(3, new ArrayList<>());
        destinies.put(0, new ArrayList<>());
        destinies.put(1, new ArrayList<>());
        destinies.put(2, new ArrayList<>());
        destinies.put(3, new ArrayList<>());
        ArrayList<Crossroad> crossroads = new ArrayList<>();
        for (int y = 0; y < map.size(); y++) {
            ArrayList<MapSquare> line = map.get(y);
            for (int x = 0; x < line.size(); x++) {
                MapSquare ms = line.get(x);
                if (ms instanceof TrafficLight) {
                    tls.add((TrafficLight) ms);
                } else if (ms instanceof Vehicle) {
                    System.out.println("Map should start with no vehicle");
                } else if (ms instanceof Spawn) {
                    spawns.get(((Spawn) ms).getCardinalDirection()).add((Spawn) ms);
                } else if (ms instanceof Destiny) {
                    Destiny d = ((Destiny) ms);
                    d.setCoords(new Coords(x, y));
                    destinies.get(d.getCardinalDirection()).add(d);
                } else if (ms instanceof Crossroad) {
                    if (!crossroads.contains(ms)) {
                        crossroads.add((Crossroad) ms);
                        ((Crossroad) ms).setCoords(new Coords(x, y));
                    }
                }
            }
        }
    }

    public void printMap() {
        for (ArrayList<MapSquare> line : map) {
            for (MapSquare ms : line) {
                System.out.print(ms.getStringValue() + " ");
            }
            System.out.println();
        }
    }

    public void setTrafficDataset(Dataset d) {
        this.traffic = d;
    }

    public Dataset getTraffic() {
        return traffic;
    }

    public Coords getCoordsAhead(int x, int y, int direction) {
        switch (direction) {
            case 0:
                return new Coords(x, y - 1);
            case 1:
                return new Coords(x, y + 1);
            case 2:
                return new Coords(x - 1, y);
            case 3:
                return new Coords(x + 1, y);
        }
        System.out.println("This is not supposed to return null, something is wrong");
        return null;
    }

    public Coords getCoordsAhead(Coords c, int direction) {
        return getCoordsAhead(c.getX(), c.getY(), direction);
    }
}
