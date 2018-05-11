package aasma;

public class Car {
    private boolean isPrioritary=false;
    private String destination;

    public Car(boolean is_prioritary, String destination){
        is_prioritary = is_prioritary;
        destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isPrioritary() {
        return isPrioritary;
    }

    public void setPrioritary(boolean prioritary) {
        isPrioritary = prioritary;
    }


}
