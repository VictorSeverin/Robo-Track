package com.mycompany.movableObjects;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.mycompany.a3.MovableObject;
import com.mycompany.interfaces.ICollider;

import subObjects.DroneArm;
import subObjects.Wheel;

public class Drone extends MovableObject {
	Random rand = new Random();
	private int width;
	private int height;
	private Point top, bottomLeft, bottomRight;
	DroneArm[] droneArms = new DroneArm[4];
	Wheel[] blades = new Wheel[4];

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
		super(size, locationX, locationY, heading, speed, 0, 0, 0);
		top = new Point(0, size / 2);
		bottomLeft = new Point(-size / 2, -size / 2);
		bottomRight = new Point(size / 2, -size / 2);
		translate(50, 50);
		DroneArm upperLeftArm = new DroneArm(ColorUtil.BLACK);
		upperLeftArm.rotate(40);
		upperLeftArm.translate(0, 12);
		DroneArm upperRightArm = new DroneArm(ColorUtil.BLACK);
		upperRightArm.rotate(-40);
		upperRightArm.translate(11, 14);
		DroneArm lowerLeftArm = new DroneArm(ColorUtil.BLACK);
		lowerLeftArm.rotate(140);
		lowerLeftArm.translate(4, 1);
		DroneArm lowerRightArm = new DroneArm(ColorUtil.BLACK);
		lowerRightArm.rotate(-140);
		lowerRightArm.translate(14, 3);
		Wheel blade1 = new Wheel(ColorUtil.BLACK);
		Wheel blade2 = new Wheel(ColorUtil.BLACK);
		Wheel blade3 = new Wheel(ColorUtil.BLACK);
		Wheel blade4 = new Wheel(ColorUtil.BLACK);
		blade1.translate(-16, 23);
		blade2.translate(-15, -18);
		blade3.translate(21, 23);
		blade4.translate(19, -18);
		droneArms[0] = upperLeftArm;
		droneArms[1] = upperRightArm;
		droneArms[2] = lowerLeftArm;
		droneArms[3] = lowerRightArm;
		blades[0] = blade1;
		blades[1] = blade2;
		blades[2] = blade3;
		blades[3] = blade4;
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
		g.fillRect(pCmpRelPrnt.getX(),
				pCmpRelPrnt.getY(), this.getSize() / 2, this.getSize() / 2);
		for (DroneArm arm : droneArms) {
			arm.draw(g, pCmpRelPrnt, pCmpRelScrn);
		}
		for (Wheel blade : blades) {
			blade.draw(g, pCmpRelPrnt, pCmpRelScrn);
		}
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
