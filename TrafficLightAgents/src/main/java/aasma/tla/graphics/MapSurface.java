package aasma.tla.graphics;

import aasma.tla.TrafficLightAgents;
import aasma.tla.maps.Map;
import aasma.tla.msquare.MapSquare;
import aasma.tla.msquare.Vehicle;

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
    private int count = 0;
    private boolean printMap;

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
//        drawGrid(g2d);
    }

    private void drawMapSquares(Graphics2D g2d) {
        int tx = 0;
        int ty = 0;
        int sizeSq = size / 4;
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
    }
}
