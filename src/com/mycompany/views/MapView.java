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
    private Point pPrevDragLoc = new Point(-1, -1);

    public MapView() {
        this.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
        this.winLeft = 0;
        this.winBottom = 0;
        this.winRight = 931 / 2;
        this.winTop = 639 / 2;
        this.winWidth = winRight - winLeft;
        this.winHeight = winTop - winBottom;
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
        gXform.translate(getAbsoluteX(), getAbsoluteY());
        gXform.concatenate(theVTM);
        gXform.translate(-getAbsoluteX(), -getAbsoluteY()); // local origin xform
        g.setTransform(gXform);

        Point pCmpRelPrnt = new Point(getX(), getY());
        Point pCmpRelScrn = new Point(getAbsoluteX(), getAbsoluteY());
        IIterator iter = gw.getObjects().getIterator();

        while (iter.hasNext()) {
            GameObject obj = iter.getNext();
            obj.draw(g, pCmpRelPrnt, pCmpRelScrn);
        }
        g.resetAffine();
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

    public void zoom(float factor) {
        float newWinLeft = winLeft + winWidth * factor;
        float newWinRight = winRight - winWidth * factor;
        float newWinTop = winTop - winHeight * factor;
        float newWinBottom = winBottom + winHeight * factor;
        float newWinHeight = newWinTop - newWinBottom;
        float newWinWidth = newWinRight - newWinLeft;
        if (newWinWidth <= 1000 && newWinHeight <= 1000 && newWinWidth > 0 && newWinHeight > 0) {
            winLeft = newWinLeft;
            winRight = newWinRight;
            winTop = newWinTop;
            winBottom = newWinBottom;
        } else
            System.out.println("Cannot zoom further!");
        this.repaint();
    }

    public void panHorizontal(double delta) {
        winLeft += delta;
        winRight += delta;
        this.repaint();
    }

    public void panVertical(double delta) {
        winBottom += delta;
        winTop += delta;
        this.repaint();
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

    @Override
    public boolean pinch(float scale) {
        if (scale < 1.0) {
            // Zooming Out: two fingers come closer together (on actual device), right mouse
            // click + drag towards the top left corner of screen (on simulator)
            zoom(-0.01f);
        } else if (scale > 1.0) {
            // Zooming In: two fingers go away from each other (on actual device), right
            // mouse
            // click + drag away from the top left corner of screen (on simulator)
            zoom(0.01f);
        }
        return true;
    }

    @Override
    public void pointerDragged(int x, int y) {
        if (pPrevDragLoc.getX() != -1) {
            if (pPrevDragLoc.getX() < x)
                panHorizontal(5);
            else if (pPrevDragLoc.getX() > x)
                panHorizontal(-5);
            if (pPrevDragLoc.getY() < y)
                panVertical(-5);
            else if (pPrevDragLoc.getY() > y)
                panVertical(5);
        }
        pPrevDragLoc.setX(x);
        pPrevDragLoc.setY(y);
    }
}
