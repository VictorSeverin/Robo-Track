package com.mycompany.movableObjects;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.MovableObject;
import com.mycompany.interfaces.ICollider;

public class Drone extends MovableObject {
	Random rand = new Random();
	private int width;
	private int height;

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

	}

	@Override
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.rgb(this.getRed(), this.getGreen(), this.getBlue()));
		int[] xCoordinates = {
				(int) p.getX() + (int) this.getLocationX() - (this.getSize() / 2),
				((int) p.getX() + (int) this.getLocationX()),
				((int) p.getX() + (int) this.getLocationX()) + (this.getSize() / 2) };
		int[] yCoordinates = {
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize() / 2),
				((int) p.getY() + (int) this.getLocationY()) + (this.getSize() / 2),
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize() / 2) };

		g.drawPolygon(xCoordinates, yCoordinates, 3);

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
