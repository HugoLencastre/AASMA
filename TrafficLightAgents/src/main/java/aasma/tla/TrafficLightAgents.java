package aasma.tla;

import aasma.tla.maps.BasicMap;
import aasma.tla.maps.Map;
import aasma.tla.traffic.BasicDataset;

public class TrafficLightAgents {
    public static void main(String[] args) {

        //get number of crossing ahead chose one of them to turn
        Map map = BasicMap.getInstance();
        map.setTrafficDataset(new BasicDataset());
        for (int i = 0; i < 10; i++) {
            map.doMapStep(true);
            System.out.println();
        }
    }
}