package com.mycompany.movableObjects;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.mycompany.a3.MovableObject;
import com.mycompany.interfaces.ICollider;

public class Drone extends MovableObject {
	Random rand = new Random();
	private int width;
	private int height;
	private Point top, bottomLeft, bottomRight;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param color     - color of the object
	 * @param heading   - angle of where the object is headed
	 * @param speed     - speed of the object
	 * 
	 **/
	public Drone(int size, double locationX, double locationY, int heading, int speed) {
		super(size, locationX, locationY, heading, speed, 25, 2, 232);
		top = new Point(0, size / 2);
		bottomLeft = new Point(-size / 2, -size / 2);
		bottomRight = new Point(size / 2, -size / 2);
		// translate((float) locationX, (float) locationY);
		translate(50, 50);
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
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

	public void move(int width, int height, int elapsedTime) {
		super.setHeading(this.getHeading() + 0.5);
		// super.move(width, height, elapsedTime);
		float deltaX = (float) Math.cos(Math.toRadians(90 - this.getHeading())) * (float) this.getSpeed();
		float deltaY = (float) Math.sin(Math.toRadians(90 - this.getHeading())) * (float) this.getSpeed();
		this.translate(deltaX, deltaY);
		this.width = width;
		this.height = height;

	}

	@Override
	public String toString() {
		return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=["
				+ this.getColor() + "] heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size="
				+ this.getSize();
	}

}
