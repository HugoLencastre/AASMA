package aasma.tla;

import aasma.tla.agents.Agent;
import aasma.tla.agents.BasicAgent;
import aasma.tla.agents.SmartAgent;
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
    public final static int DELAY = 200;
    public final static boolean SHOW_NR_OF_VEHICLES_NEAR_CROSS = true;
    public final static boolean REALISTIC_REACTIONS = true;

    public TrafficLightAgents() {
        initUI();
    }

    private void initUI() {

        Map map = FourCrossMap.getInstance().setTrafficDataset(new BasicDataset()).setAgent(new SmartAgent());
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