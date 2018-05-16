package aasma.tla.msquare;

public class Crossroad extends Road {

    private Coords coords;

    @Override
    public String getStringValue() {
        return "C";
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
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
}
