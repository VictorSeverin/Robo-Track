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
    private int width;
    private int height;
    private IStrategy currIStrategy;

    public NonPlayerRobot(int size, double locationX, double locationY, IStrategy initialStrategy) {
        super(size, locationX, locationY, 3);
        this.currIStrategy = initialStrategy;
        System.out.println(this.getEnergyLevel() + " " + this.getEnergyConsumptionRate());
    }

    @Override
    public void draw(Graphics g, Point p) {
        g.setColor(ColorUtil.BLACK);
        g.drawRect((int) (p.getX() + this.getLocationX() - (this.getSize() / 2)),
                (int) (p.getY() + this.getLocationY() - (this.getSize() / 2)), this.getSize(), this.getSize());
        g.setColor(ColorUtil.rgb(255, 0, 0));
        int radius = (int) Math
                .sqrt((this.getSize() / 2 * this.getSize() / 2) + ((this.getSize() / 2) * (this.getSize() / 2)));
        g.drawArc((int) (p.getX() + this.getLocationX()) - radius, (int) (p.getY() +
                this.getLocationY()) - radius, radius * 2, radius * 2, 0, 360);
        g.setColor(ColorUtil.BLACK);

    }

    public void move(GameWorld gw, NonPlayerRobot npr, int width, int height, int elapsedTime) {
        if (this.getEnergyLevel() == 0) {
            this.setEnergyLevel(1000);
        }
        invokeStrategy(gw, npr, elapsedTime);
        super.setHeading(this.getSteeringDirection());
        super.move(width, height, elapsedTime);

    }

    public String getStrategy() {
        return this.currIStrategy.getName();
    }

    public void setStrategy(IStrategy strategy) {
        this.currIStrategy = strategy;
    }

    public void invokeStrategy(GameWorld gw, NonPlayerRobot npr, int elapsedTime) {
        this.currIStrategy.apply(gw, npr);
        super.move(width, height, elapsedTime);
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
