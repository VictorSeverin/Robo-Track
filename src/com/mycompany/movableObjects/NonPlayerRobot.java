package com.mycompany.movableObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.util.MathUtil;
import com.mycompany.a3.GameCollection;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.fixedObjects.Base;
import com.mycompany.interfaces.IIterator;
import com.mycompany.interfaces.IStrategy;

public class NonPlayerRobot extends Robot implements IStrategy {
    private int steeringDirection;
    private int maximumSpeed;
    private int energyLevel;
    private int energyConsumptionRate;
    private int damageLevel;
    private int lastBaseReached;
    private int maxDamageLevel;
    private int strategy;

    public NonPlayerRobot(int size, double locationX, double locationY, int initialStrategy) {
        super(size, locationX, locationY);
        super.setSpeed(15);
        this.maximumSpeed = 50;
        this.energyLevel = 250;
        this.damageLevel = 0;
        this.energyConsumptionRate = 5;
        this.lastBaseReached = 1;
        this.steeringDirection = 90;
        this.maxDamageLevel = 100;
        this.strategy = initialStrategy;
    }

    @Override
    public void draw(Graphics g, Point p) {
        g.setColor(ColorUtil.BLACK);
        g.drawRect((int) (p.getX() + this.getLocationX()), (int) (p.getY() + this.getLocationY()), this.getSize(),
                this.getSize());
    }

    public void move() {
        if (this.energyLevel <= 0) {
            this.energyLevel = 250;
        }
        if (this.energyLevel > 0 && this.damageLevel < this.maxDamageLevel && this.getSpeed() > 0) {
            super.setHeading(this.getHeading() + this.steeringDirection);
        }
        this.energyLevel -= this.energyConsumptionRate;
        super.move();
    }

    public void setStrategy() {
        if (strategy == 1) {
            this.strategy = 2;
        } else {
            this.strategy = 1;
        }
        this.lastBaseReached++;
        super.setBase(this.getLastBaseReached() + 1);
    }

    public void invokeStrategy(GameWorld gw) {
        GameCollection objects = gw.getObjects();
        // go to the next base
        if (strategy == 1) {
            IIterator iter = objects.getIterator();
            while (iter.hasNext()) {
                GameObject obj = iter.getNext();
                if (obj instanceof Base) {
                    Base base = (Base) obj;
                    if (base.getSequenceNumber() == this.lastBaseReached + 1) {
                        double a = base.getLocationX() - this.getLocationX();
                        double b = base.getLocationY() - this.getLocationY();
                        double idealHeading = MathUtil.atan2(a, b);
                        this.setHeading(Math.floor(Math.toDegrees(90 - idealHeading)));
                    }
                }
            }
            super.move();
        } // attach the player robot
        else if (strategy == 2) {
            IIterator iter = objects.getIterator();
            while (iter.hasNext()) {
                GameObject obj = iter.getNext();
                if (obj instanceof Robot) {
                    Robot player = (Robot) obj;
                    double a = player.getLocationX() - this.getLocationX();
                    double b = player.getLocationY() - this.getLocationY();
                    double idealHeading = MathUtil.atan2(a, b);
                    this.setHeading(Math.floor(Math.toDegrees(90 - idealHeading)));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
                + Math.round(this.getLocationY() * 10.0) / 10.0 + " color=["
                + this.getColor() + "] heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size="
                + this.getSize() + " maxSpeed=" + this.getMaximumSpeed() + " steeringDirection="
                + this.getSteeringDirection()
                + " energyLevel=" + this.getEnergyLevel() + " damageLevel= " + this.getDamageLevel()
                + " Current Strategy=" + this.strategy;
    }
}
