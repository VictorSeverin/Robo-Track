package com.mycompany.movableObjects;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.util.MathUtil;
import com.mycompany.a3.GameCollection;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.fixedObjects.Base;
import com.mycompany.interfaces.IIterator;
import com.mycompany.interfaces.IStrategy;

import subObjects.Wheel;

public class NonPlayerRobot extends Robot {
	private int width;
	private int height;
	private IStrategy currIStrategy;
	private Point topLeft, topRight, bottomLeft, bottomRight;
	Wheel[] wheels = new Wheel[4];

	public NonPlayerRobot(int size, double locationX, double locationY, IStrategy initialStrategy) {
		super(size, locationX, locationY, 1, 0, 0, 0);
		this.currIStrategy = initialStrategy;
		topLeft = new Point(-size / 2, size / 2);
		topRight = new Point(size / 2, size / 2);
		bottomLeft = new Point(-size / 2, -size / 2);
		bottomRight = new Point(size / 2, -size / 2);
		translate(100, 100);
		Wheel w1 = new Wheel(ColorUtil.rgb(255, 0, 0));
		w1.translate(-5, -6);
		Wheel w2 = new Wheel(ColorUtil.rgb(255, 0, 0));
		w2.translate(16, -6);
		Wheel w3 = new Wheel(ColorUtil.rgb(255, 0, 0));
		w3.translate(-5, 18);
		Wheel w4 = new Wheel(ColorUtil.rgb(255, 0, 0));
		w4.translate(16, 18);
		wheels[0] = w1;
		wheels[1] = w2;
		wheels[2] = w3;
		wheels[3] = w4;
	}

	@Override
	public void draw(Graphics g, Point p, Point pCmpRelScrn) {
		g.setColor(ColorUtil.rgb(this.getRed(), this.getGreen(), this.getBlue()));
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
		g.fillRect(p.getX(),
				p.getY(), this.getSize() - 10, this.getSize());
		for (Wheel wheel : wheels) {
			wheel.draw(g, p, pCmpRelScrn);
		}
		g.setTransform(copy);

	}

	public void move(GameWorld gw, NonPlayerRobot npr, int width, int height, int elapsedTime) {
		if (this.getEnergyLevel() == 0) {
			this.setEnergyLevel(1000);
		}
		invokeStrategy(gw, npr, elapsedTime);
		super.setHeading(this.getSteeringDirection());
		float deltaX = (float) Math.cos(Math.toRadians(90 - this.getHeading())) * (float) this.getSpeed();
		float deltaY = (float) Math.sin(Math.toRadians(90 - this.getHeading())) * (float) this.getSpeed();
		translate(deltaX, deltaY);
		// super.move(width, height, elapsedTime);

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
