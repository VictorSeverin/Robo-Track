package com.mycompany.strategies;

import com.mycompany.a3.GameCollection;
import com.mycompany.a3.GameWorld;
import com.mycompany.interfaces.IStrategy;
import com.mycompany.movableObjects.NonPlayerRobot;
import com.mycompany.interfaces.IIterator;
import com.mycompany.a3.GameObject;
import com.mycompany.fixedObjects.Base;
import com.mycompany.fixedObjects.EnergyStation;
import com.codename1.util.MathUtil;

public class NextBase implements IStrategy {

    public String getName() {
        return "NextBase";
    }

    public void apply(GameWorld gw, NonPlayerRobot npr) {
        GameCollection objects = gw.getObjects();
        IIterator iter = objects.getIterator();
        Base base = null;
        while (iter.hasNext()) {
            GameObject obj = iter.getNext();
            if (obj instanceof Base) {
                base = (Base) obj;
                if (base.getSequenceNumber() == npr.getLastBaseReached() + 1) {
                    double a = base.getLocationX() - npr.getLocationX();
                    double b = base.getLocationY() - npr.getLocationY();
                    double idealHeading = 90 - MathUtil.atan2(b, a);
                    npr.setSteeringDirection((int) Math.floor(Math.toDegrees(idealHeading)));
                }
            }
        }
    }
}
