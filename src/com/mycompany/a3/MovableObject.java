package com.mycompany.a3;

import java.lang.Math;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.BrowserWindow.EvalRequest;

public abstract class MovableObject extends GameObject {
	private double heading;
	private double speed;

	public MovableObject(int size, double locationX, double locationY, int heading, int speed, int red, int green,
			int blue) {
		super(size, locationX, locationY, red, green, blue);
		this.heading = heading;
		this.speed = speed;
		// System.out.println(this.getClass() + " " + this.getHeading());
	}

	public void move(int width, int height, int elapsedTime) {
		// double dist = this.getSpeed() * (elapsedTime / 1000);
		// double deltaX = Math.cos(Math.toRadians(90 - this.getHeading())) * speed;
		// double deltaY = Math.sin(Math.toRadians(90 - this.getHeading())) * speed;
		// double newX = super.getLocationX() + deltaX;
		// double newY = super.getLocationY() + deltaY;
		if ((this.getLocationX() + this.getSize() / 2) >= width) {
			this.setHeading(-this.getHeading());
		}
		if ((this.getLocationX() - this.getSize() / 2) <= 0) {
			this.setHeading(-this.getHeading());
		}
		if ((this.getLocationY() + this.getSize() / 2) >= height - 100) {
			this.setHeading(180);
		}
		if ((this.getLocationY() - this.getSize() / 2) <= 0) {
			this.setHeading(0);
		}
		// System.out.println("From Movable__________ W: " + width + "H: " + height);
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setHeading(double heading) {
		this.heading = heading;
		// setRotate(heading);
	}

	public double getHeading() {
		return this.heading;
	}

	public double getSpeed() {
		return this.speed;
	}

}
