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
	private Transform myRotation, myTranslation, myScale;

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
		myRotation = Transform.makeIdentity();
		myTranslation = Transform.makeIdentity();
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

	public void resetTransform() {
		myRotation.setIdentity();
		myTranslation.setIdentity();
		myScale.setIdentity();
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
		g.setColor(ColorUtil.rgb(this.getRed(), this.getGreen(), this.getBlue()));
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		gXform.translate(pCmpRelScrn.getX(), pCmpRelScrn.getY());
		gXform.translate(myTranslation.getTranslateX(),
				myTranslation.getTranslateY());
		gXform.concatenate(myRotation);
		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
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

	}

	public void move(int width, int height, int elapsedTime) {
		super.setHeading(this.getHeading() + 0.5);
		super.move(width, height, elapsedTime);
		this.width = width;
		this.height = height;
		// System.out.println("X:" + this.getLocationX() + " Y: " + this.getLocationY()
		// + "W: " + width + " H: " + height);

	}

	@Override
	public String toString() {
		return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=["
				+ this.getColor() + "] heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size="
				+ this.getSize();
	}

}
