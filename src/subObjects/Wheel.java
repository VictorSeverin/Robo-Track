package subObjects;

import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

public class Wheel {
    private Point center;
    private int myColor;
    private Transform myTranslation;
    private Transform myRotation;
    private Transform myScale;

    public Wheel() {
        myTranslation = Transform.makeIdentity();
        myRotation = Transform.makeIdentity();
        myScale = Transform.makeIdentity();
    }

    public void rotate(float degrees) {
        myRotation.rotate((float) Math.toRadians(degrees), 0, 0);
    }

    public void translate(float tx, float ty) {
        myTranslation.translate(tx, ty);
    }

    public void scale(float sx, float sy) {
        myScale.scale(sx, sy);
    }

    public Transform getTranslate() {
        return myTranslation;
    }

    public Transform getScale() {
        return myScale;
    }

    public Transform getRotation() {
        return myRotation;
    }

    public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
        Transform gXform = Transform.makeIdentity();
        g.getTransform(gXform);
        Transform copy = gXform.copy();
        gXform.translate(pCmpRelScrn.getX(), pCmpRelScrn.getY());
        gXform.translate(getTranslate().getTranslateX(),
                getTranslate().getTranslateY());
        gXform.concatenate(getRotation());
        gXform.scale(getScale().getScaleX(), getScale().getScaleY());
        gXform.translate(-pCmpRelScrn.getX(), -pCmpRelScrn.getY());
        g.setTransform(gXform);
        // draw the lines as before
        g.drawArc(pCmpRelPrnt.getX(),
                pCmpRelPrnt.getY(), 15, 15, 0, 360);
        g.setTransform(copy);
    }
}
