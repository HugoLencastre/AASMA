package aasma.tla;

import aasma.tla.graphics.MapSurface;
import aasma.tla.maps.BasicMap;
import aasma.tla.maps.FourCrossMap;
import aasma.tla.maps.Map;
import aasma.tla.maps.TwoCrossMap;
import aasma.tla.traffic.BasicDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrafficLightAgents extends JFrame {

    public final static int SPOT_SIZE = 30;
    public final static int DELAY = 500;
    public final static boolean SHOW_NR_OF_VEHICLES_NEAR_CROSS = true;
    //this is not actually realistic, because it only works well in half of the directions
    //things such as iteration would have to change for it to work correctly
    // not currently working
    public final static boolean REALISTIC_REACTIONS = false;

    public TrafficLightAgents() {
        initUI();
    }

    private void initUI() {

        Map map = BasicMap.getInstance().setTrafficDataset(new BasicDataset());
        final MapSurface surface = new MapSurface(DELAY, map, false);
        add(surface);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
            }
        });

        setTitle("Traffic");
        setSize((map.width + 1) * SPOT_SIZE, (map.height + 2) * SPOT_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            TrafficLightAgents ex = new TrafficLightAgents();
            ex.setVisible(true);
        });

    }
}