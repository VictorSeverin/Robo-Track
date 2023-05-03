package com.mycompany.views;

import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.plaf.Border;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Dimension;
import com.mycompany.a3.FixedObject;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.interfaces.IIterator;
import com.mycompany.interfaces.ISelectable;

public class MapView extends Container implements Observer {
    GameWorld gw = new GameWorld();
    private float winLeft, winBottom, winRight, winTop, winWidth, winHeight;
    Transform worldtoNd, ndToDisplay, theVTM;

    public MapView() {
        this.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
        this.winLeft = 0;
        this.winBottom = this.getWidth() / 2;
        this.winTop = this.getHeight() / 2;
    }

    public void update(Observable o, Object data) {
        gw = (GameWorld) data;
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.winWidth = winRight - winLeft;
        this.winHeight = winTop - winBottom;
        worldtoNd = buildWorldToNDXform(winWidth, winHeight, winLeft, winBottom);
        ndToDisplay = buildNDToDisplayXform(this.getWidth(), this.getHeight());
        theVTM = ndToDisplay.copy();
        theVTM.concatenate(worldtoNd);
        Transform gXform = Transform.makeIdentity();
        g.getTransform(gXform);
        gXform.translate(getAbsoluteX(), getAbsoluteY()); // local origin xform (part 2)
        gXform.concatenate(theVTM); // VTM xform
        gXform.translate(-getAbsoluteX(), -getAbsoluteY()); // local origin xform (part 1)
        g.setTransform(gXform);
        Point pCmpRelPrnt = new Point(this.getX(), this.getY());
        Point pCmpRelScrn = new Point(getAbsoluteX(), getAbsoluteY());
        IIterator iter = gw.getObjects().getIterator();
        Point p = new Point(getX(), getY());
        while (iter.hasNext()) {
            GameObject obj = iter.getNext();
            obj.draw(g, pCmpRelPrnt);
        }
    }

    private Transform buildNDToDisplayXform(float displayWidth, float displayHeight) {
        Transform tmpXfrom = Transform.makeIdentity();
        tmpXfrom.translate(0, displayHeight);
        tmpXfrom.scale(displayWidth, -displayHeight);
        return tmpXfrom;
    }

    private Transform buildWorldToNDXform(float winWidth, float winHeight, float winLeft, float winBottom) {
        Transform tmpXfrom = Transform.makeIdentity();
        tmpXfrom.scale((1 / winWidth), (1 / winHeight));
        tmpXfrom.translate(-winLeft, -winBottom);
        return tmpXfrom;
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
