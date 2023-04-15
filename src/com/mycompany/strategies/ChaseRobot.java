package com.mycompany.strategies;

import com.mycompany.a3.GameCollection;
import com.mycompany.a3.GameWorld;
import com.mycompany.interfaces.IStrategy;
import com.mycompany.movableObjects.NonPlayerRobot;
import com.mycompany.movableObjects.PlayerRobot;
import com.mycompany.interfaces.IIterator;
import com.mycompany.a3.GameObject;
import com.codename1.util.MathUtil;

public class ChaseRobot implements IStrategy {

    public String getName() {
        return "ChaseRobot";
    }

    public void apply(GameWorld gw, NonPlayerRobot npr) {
        GameCollection objects = gw.getObjects();
        IIterator iter = objects.getIterator();
        PlayerRobot pr = null;
        while (iter.hasNext()) {
            GameObject obj = iter.getNext();
            if (obj instanceof PlayerRobot) {
                pr = (PlayerRobot) obj;
            }
        }
        double a = pr.getLocationX() - npr.getLocationX();
        double b = pr.getLocationY() - npr.getLocationY();
        double idealHeading = 90 - MathUtil.atan2(b, a);
        npr.setSteeringDirection((int) Math.floor(Math.toDegrees(idealHeading)));

    }
}
