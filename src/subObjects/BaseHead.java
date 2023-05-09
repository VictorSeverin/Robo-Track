package subObjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.SubObject;

public class BaseHead extends SubObject {
    private Point center;
    private int myColor;
    private Point top, bottomLeft, bottomRight;

    public BaseHead(Point top, Point bottomLeft, Point bottomRight) {
        this.myColor = ColorUtil.BLACK;
        this.top = top;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
        g.setColor(myColor);
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
        g.drawLine(pCmpRelPrnt.getX() + top.getX(), pCmpRelPrnt.getY() + top.getY(),
                pCmpRelPrnt.getX() + bottomLeft.getX(),
                pCmpRelPrnt.getY() + bottomLeft.getY());
        g.drawLine(pCmpRelPrnt.getX() + bottomLeft.getX(),
                pCmpRelPrnt.getY() + bottomLeft.getY(),
                pCmpRelPrnt.getX() + bottomRight.getX(),
                pCmpRelPrnt.getY() + bottomRight.getY());
        g.drawLine(pCmpRelPrnt.getX() + bottomRight.getX(),
                pCmpRelPrnt.getY() + bottomRight.getY(),
                pCmpRelPrnt.getX() + top.getX(),
                pCmpRelPrnt.getY() + top.getY());
        g.setTransform(copy);
    }
}
