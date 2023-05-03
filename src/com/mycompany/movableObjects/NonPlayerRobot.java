package com.mycompany.movableObjects;

import com.codename1.ui.geom.Point;
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
		super(size, locationX, locationY, 3, 0, 0, 0);
		this.currIStrategy = initialStrategy;
	}

	@Override
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.rgb(this.getRed(), this.getGreen(), this.getBlue()));
		g.drawRect((int) (p.getX() + this.getLocationX() - (this.getSize() / 2)),
				(int) (p.getY() + this.getLocationY() - (this.getSize() / 2)), this.getSize(), this.getSize());

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
