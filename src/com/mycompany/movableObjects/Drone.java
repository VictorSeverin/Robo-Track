package com.mycompany.movableObjects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.MovableObject;

public class Drone extends MovableObject {
	Random rand = new Random();

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
		super(size, locationX, locationY, ColorUtil.BLUE, heading, speed);
	}

	@Override
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.BLUE);
		int[] xCoordinates = {
				(int) p.getX() + (int) this.getLocationX() - (this.getSize()),
				((int) p.getX() + (int) this.getLocationX()),
				((int) p.getX() + (int) this.getLocationX()) + (this.getSize()) };
		int[] yCoordinates = {
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize()),
				((int) p.getY() + (int) this.getLocationY()) + (this.getSize()),
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize()) };
		g.drawPolygon(xCoordinates, yCoordinates, 3);
		g.setColor(ColorUtil.rgb(255, 0, 0));
		int radius = (int) Math.sqrt((this.getSize() * this.getSize()) + ((this.getSize()) * (this.getSize())));
		g.drawArc((int) (p.getX() + this.getLocationX()) - radius, (int) (p.getY() +
				this.getLocationY()) - radius, radius * 2, radius * 2, 0, 360);

		g.fillRect((int) (p.getX() + this.getLocationX()),
				(int) (p.getY() + this.getLocationY()), 10,
				10);
	}

	public void move(int height, int width, int elapsedTime) {
		super.setHeading(this.getHeading() + 1);
		if (this.getLocationX() > width || this.getLocationX() < 1 || this.getLocationY() > height
				|| this.getLocationY() < 1) {
			this.setHeading(this.getHeading() + 180);
		}
		super.move(width, height, elapsedTime);
		System.out.println("X:" + this.getLocationX() + " Y: " + this.getLocationY() + "W: " + width + " H: " + height);
	}

	@Override
	public void setColor(int color) {
	}

	@Override
	public String toString() {
		return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=["
				+ this.getColor() + "] heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size="
				+ this.getSize();
	}

}
