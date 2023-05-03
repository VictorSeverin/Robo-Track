package com.mycompany.views;

import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.plaf.Border;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.mycompany.a3.FixedObject;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.interfaces.IIterator;
import com.mycompany.interfaces.ISelectable;

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

    public void pointerPressed(int x, int y) {
        x = x - getAbsoluteX();
        y = y - getAbsoluteY();
        Point pPtrRelPrnt = new Point(x, y);
        Point pCmpRelPrnt = new Point(getX(), getY());
        IIterator iter = gw.getObjects().getIterator();
        if (gw.isPaused()) {
            while (iter.hasNext()) {
                GameObject obj = iter.getNext();
                if (obj instanceof FixedObject) {
                    FixedObject fixedObj = (FixedObject) obj;
                    if (fixedObj.isSelected() && gw.getPositionClicked()) {
                        fixedObj.setLocationX(x);
                        fixedObj.setLocationY(y);
                        gw.setPosition(false);
                        fixedObj.setSelected(false);
                    }
                    if (fixedObj.contains(pPtrRelPrnt, pCmpRelPrnt)) {
                        fixedObj.setSelected(true);
                        // Point newLoc = new Point(x,y);
                    } else {
                        fixedObj.setSelected(false);
                    }
                }
            }
        }
        repaint();
    }
}
