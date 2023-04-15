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

public class NonPlayerRobot extends Robot {
    private int steeringDirection;
    private int maximumSpeed;
    private int energyLevel;
    private int energyConsumptionRate;
    private int damageLevel;
    private int lastBaseReached;
    private int maxDamageLevel;
    private int width;
    private int height;
    private IStrategy currIStrategy;

    public NonPlayerRobot(int size, double locationX, double locationY, IStrategy initialStrategy) {
        super(size, locationX, locationY);
        super.setSpeed(15);
        this.maximumSpeed = 50;
        this.energyLevel = 250;
        this.damageLevel = 0;
        this.energyConsumptionRate = 5;
        this.lastBaseReached = 1;
        this.steeringDirection = 90;
        this.maxDamageLevel = 100;
        this.currIStrategy = initialStrategy;
        // System.out.println("New Npr: " + this);
    }

    @Override
    public void draw(Graphics g, Point p) {
        g.setColor(ColorUtil.BLACK);
        g.drawRect((int) (p.getX() + this.getLocationX() + (this.getSize() / 2)),
                (int) (p.getY() + this.getLocationY() + (this.getSize() / 2)), this.getSize(), this.getSize());
    }

    public String getStrategy() {
        return this.currIStrategy.getName();
    }

    public void setStrategy(IStrategy strategy) {
        this.currIStrategy = strategy;
    }

    public void invokeStrategy(GameWorld gw, NonPlayerRobot npr) {
        this.currIStrategy.apply(gw, npr);
        super.move(width, height);
    }

    @Override
    public String toString() {
        return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
                + Math.round(this.getLocationY() * 10.0) / 10.0 + " color=[" + this.getColor() + "] heading="
                + this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed="
                + this.getMaximumSpeed() + " steeringDirection=" + this.getSteeringDirection() + " energyLevel="
                + this.getEnergyLevel() + " damageLevel= " + this.getDamageLevel() + " Current Strategy="
                + this.currIStrategy.getName();
    }
}
