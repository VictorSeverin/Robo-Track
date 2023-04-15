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
		int[] xCoordinates = { (int) p.getX() + (int) this.getLocationX() - 50,
				(int) p.getX() + (int) this.getLocationX(),
				(int) p.getX() + (int) this.getLocationX() + 50 };
		int[] yCoordinates = { (int) p.getY() + (int) this.getLocationY() - 50,
				(int) p.getY() + (int) this.getLocationY(),
				(int) p.getY() + (int) this.getLocationY() - 50 };
		g.drawPolygon(xCoordinates, yCoordinates, 3);
		g.setColor(ColorUtil.rgb(255, 0, 0));
		g.fillArc((int) (p.getX() + this.getLocationX()), (int) (p.getY() +
				this.getLocationY()), 10, 10, 0, 360);
	}

	public void move(int height, int width) {
		super.setHeading(this.getHeading() + 5);
		if (this.getLocationX() == 0 || this.getLocationX() == 1024 || this.getLocationY() == 0
				|| this.getLocationY() == 768) {
			this.setHeading(this.getHeading() + 90); // turn the drone the other way
		}
		super.move(height, width);
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
