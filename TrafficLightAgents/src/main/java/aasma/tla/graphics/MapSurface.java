package aasma.tla.graphics;

import aasma.tla.TrafficLightAgents;
import aasma.tla.maps.Map;
import aasma.tla.msquare.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MapSurface extends JPanel implements ActionListener {

    private Timer timer;
    private Map map;
    private final int size = TrafficLightAgents.SPOT_SIZE;
    private int mh;
    private int mw;
    private static int count = 0;
    private boolean printMap;

    private double atm = 0;
    private double awt = 0;

    public MapSurface(int delay, Map map, boolean printMap) {
        initTimer(delay);
        this.map = map;
        mh = map.height*size;
        mw = map.width*size;
        this.printMap = printMap;
    }

    private void initTimer(int delay) {
        timer = new Timer(delay, this);
        timer.start();
    }

    public Timer getTimer() {

        return timer;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        g2d.setPaint(Color.black);

        drawMapSquares(g2d);

        g2d.setPaint(Color.black);
        g2d.drawString("Total steps: " + Integer.toString(count),10,30);
        if (atm == 0 || count%100==0) {
            atm = Destiny.getATMLastVehicles();
            awt = Destiny.getAWTLastVehicles();
        }
        g2d.drawString("Average Time in Map (last 20 vehicles): " + atm,15,50);
        g2d.drawString("Average Wait Time (last 20 vehicles): " + awt,15,70);

//        drawGrid(g2d);
    }

    private void drawMapSquares(Graphics2D g2d) {
        int tx = 0;
        int ty = 0;
        int sizeSq = size / 4;
        ArrayList<Crossroad> crs = new ArrayList<>();
        for (ArrayList<MapSquare> line : map.getMap()) {
            for (MapSquare ms : line) {
                g2d.setPaint(ms.getColorValue());
                g2d.fillRect(tx, ty, tx+size, ty+size);
                if (ms instanceof Vehicle){
                    g2d.setPaint(Color.lightGray);
                    switch (((Vehicle) ms).getCurrentDirection()) {
                        case 0:
                            g2d.fillRect(tx+sizeSq, ty, sizeSq*2, sizeSq);
                            break;
                        case 1:
                            g2d.fillRect(tx+sizeSq, ty+size-sizeSq, sizeSq*2, sizeSq);
                            break;
                        case 2:
                            g2d.fillRect(tx, ty+sizeSq, sizeSq, sizeSq*2);
                            break;
                        case 3:
                            g2d.fillRect(tx+size-sizeSq, ty+sizeSq, sizeSq, sizeSq*2);
                            break;
                    }
                } else if (ms instanceof Crossroad) {
                    if (TrafficLightAgents.SHOW_NR_OF_VEHICLES_NEAR_CROSS && !crs.contains(ms)){
                        crs.add((Crossroad) ms);
                        Coords cds = ((Crossroad) ms).getCoords();
                        g2d.setPaint(Color.BLACK);
                        g2d.drawString(Integer.toString(((Crossroad) ms).getHDirNrV(map)), (cds.getX()-2)*size, (cds.getY())*size-size/2);
                        g2d.drawString(Integer.toString(((Crossroad) ms).getVDirNrV(map)), (cds.getX()-1)*size, (cds.getY()-2)*size);
                    }
                }
                tx += size;
            }
            ty += size;
            tx = 0;
        }
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setPaint(Color.WHITE);
        for(int x = 0; x < map.width+1; x++){
            int tx = x*size;
            g2d.drawLine(tx,0, tx, mh);
        }
        for(int y = 0; y < map.height+1; y++){
            int ty = y*size;
            g2d.drawLine(0,ty, mw, ty);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        map.doMapStep(printMap, count);
        repaint();
        if (count == 5000000) {
            printInfo();
            System.exit(0);
        }
    }

    public static int getCount() {
        return count;
    }

    public static void printInfo() {
        System.out.println("Total step number: " + MapSurface.getCount());
        System.out.println("Average time in map: " + Destiny.getAverageTimeInMap());
        System.out.println("Average wait time: " + Destiny.getAverageWaitTime());
    }
}
