package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.fixedObjects.Base;
import com.mycompany.fixedObjects.EnergyStation;
import com.mycompany.interfaces.ICollider;
import com.mycompany.interfaces.IDrawable;
import com.mycompany.movableObjects.Drone;
import com.mycompany.movableObjects.NonPlayerRobot;
import com.mycompany.movableObjects.PlayerRobot;
import com.mycompany.movableObjects.Robot;

public abstract class GameObject implements IDrawable, ICollider {
	private int size;
	private double locationX;
	private double locationY;
	private int color;

	GameObject(int size, double locationX, double locationY, int color) {
		this.size = size;
		this.locationX = locationX;
		this.locationY = locationY;
		this.color = color;
	}

	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		double thisCenterX = this.getLocationX() + (this.getSize() / 2); // find centers
		double thisCenterY = this.getLocationY() + (this.getSize() / 2);
		double otherCenterX = (((GameObject) obj).getLocationX()) + ((GameObject) obj).getSize();
		double otherCenterY = (((GameObject) obj).getLocationY()) + ((GameObject) obj).getSize();
		// find dist between centers (use square, to avoid taking roots)
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		// find square of sum of radii
		int thisRadius = this.getSize() / 2;
		int otherRadius = ((GameObject) obj).getSize();
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}
		return result;
	}

	void setLocationX(double x) {
		this.locationX = x;
	}

	void setLocationY(double y) {
		this.locationY = y;
	}

	public double getLocationY() {
		return locationY;
	}

	public double getLocationX() {
		return locationX;
	}

	public int getSize() {
		return size;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
