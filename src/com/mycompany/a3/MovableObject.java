package com.mycompany.a3;

import java.lang.Math;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.codename1.ui.BrowserWindow.EvalRequest;

public abstract class MovableObject extends GameObject {
	private double heading;
	private int speed;

	public MovableObject(int size, double locationX, double locationY, int color, int heading, int speed) {
		super(size, locationX, locationY, color);
		this.heading = heading;
		this.speed = speed;
		// System.out.println(this.getClass() + " " + this.getHeading());
	}

	public void move(int width, int height, int elapsedTime) {
		// double dist = this.getSpeed() * (elapsedTime / 1000);
		double deltaX = Math.cos(Math.toRadians(90 - this.getHeading())) * speed;
		double deltaY = Math.sin(Math.toRadians(90 - this.getHeading())) * speed;

		super.setLocationX(super.getLocationX() + deltaX);
		super.setLocationY(super.getLocationY() + deltaY);

	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}

	public double getHeading() {
		return this.heading;
	}

	public int getSpeed() {
		return this.speed;
	}

}
