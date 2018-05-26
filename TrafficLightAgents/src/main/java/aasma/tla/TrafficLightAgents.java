package aasma.tla;

import aasma.tla.agents.ChangerAgent;
import aasma.tla.graphics.MapSurface;
import aasma.tla.maps.FourExtendedMap;
import aasma.tla.maps.Map;
import aasma.tla.traffic.RushHourDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TrafficLightAgents extends JFrame {

    public final static int SPOT_SIZE = 30;
    public final static int DELAY = 0;
    public final static boolean SHOW_NR_OF_VEHICLES_NEAR_CROSS = true;
    //has side effect of lower spawning capabilities
    public final static boolean REALISTIC_REACTIONS = true;
    //if the traffic lights have cooldowns and pity timers (slows agents step optionally, see line in doStep)
    public final static boolean TLTIMER = true;
    public final static int TL_MAX_TIMER = 50;
    public final static int TL_MIN_TIMER = 10;

    public TrafficLightAgents() {
        initUI();
    }

    private void initUI() {

        Map map = FourExtendedMap.getInstance().setTrafficDataset(new RushHourDataset()).setAgent(new ChangerAgent());
        final MapSurface surface = new MapSurface(DELAY, map, false);
        add(surface);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Timer timer = surface.getTimer();
                timer.stop();
                MapSurface.printInfo();
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