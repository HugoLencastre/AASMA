package aasma.tla.msquare;

public class Destiny extends MapSquare {

    private Coords coords;

    private int cardinalDirection;

    /**
     * 0-North, 1-South, 2-West, 3-East
     * @return
     */
    public Destiny setCardinalDirection(int cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
        return this;
    }

    /**
     * 0-North, 1-South, 2-West, 3-East
     * @return
     */
    public int getCardinalDirection() {
        return cardinalDirection;
    }

    @Override
    public String getStringValue() {
        return "D";
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }
}
