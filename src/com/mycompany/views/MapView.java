package com.mycompany.views;

import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.plaf.Border;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.interfaces.IIterator;

public class MapView extends Container implements Observer {
    GameWorld gw = new GameWorld();

    public MapView() {
        this.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
    }

    public void update(Observable o, Object data) {
        gw = (GameWorld) data;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        IIterator iter = gw.getObjects().getIterator();
        Point p = new Point(getX(), getY());
        while (iter.hasNext()) {
            GameObject obj = iter.getNext();
            obj.draw(g, p);
        }
    }
}
