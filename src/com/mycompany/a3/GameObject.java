package com.mycompany.a3;

import java.util.ArrayList;

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
	ArrayList<GameObject> collidingWith = new ArrayList<GameObject>();

	GameObject(int size, double locationX, double locationY, int color) {
		this.size = size;
		this.locationX = locationX;
		this.locationY = locationY;
		this.color = color;
	}

	public boolean collidesWith(ICollider obj) {
		boolean result = false;
		GameObject otherObject = (GameObject) obj;
		double thisCenterX = this.getLocationX() + (this.getSize() / 2); // find centers
		double thisCenterY = this.getLocationY() + (this.getSize() / 2);
		double otherCenterX = (otherObject.getLocationX()) + (otherObject.getSize() / 2);
		double otherCenterY = (otherObject.getLocationY()) + (otherObject.getSize() / 2);
		// find dist between centers (use square, to avoid taking roots)
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		// find square of sum of radii
		int thisRadius = this.getSize() / 2;
		int otherRadius = otherObject.getSize() / 2;
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) {
			if (!collidingWith.contains(obj)) {
				collidingWith.add(otherObject);
				System.out.println("added");
				result = true;
			}
			if (distBetweenCentersSqr > radiiSqr) {
				if (collidingWith.contains(otherObject)) {
					collidingWith.remove(otherObject);
				}
			}
		}
		// System.out.println("X:" + otherCenterX + " Y:" + otherCenterY);
		return result;
	}

	public void handleCollision(ICollider obj, GameWorld gw) {
		if (this instanceof PlayerRobot && obj instanceof EnergyStation) {
			System.out.println("Robot Collided with EnergyStation");
			EnergyStation es = (EnergyStation) obj;
			PlayerRobot pr = (PlayerRobot) this;
			gw.esCollision(es, pr);
		}
		if (this instanceof PlayerRobot && obj instanceof Base) {
			System.out.println("Robot Collided with Base");
			Base base = (Base) obj;
			PlayerRobot pr = (PlayerRobot) this;
			gw.baseCollide(pr, base);
		}
		if (this instanceof PlayerRobot && obj instanceof NonPlayerRobot) {
			System.out.println("Robot Collided with NonPlayerRobot");
			PlayerRobot pr = (PlayerRobot) this;
			NonPlayerRobot npr = (NonPlayerRobot) obj;
			gw.robotCollision(pr, npr);
		}
		if (this instanceof PlayerRobot && obj instanceof Drone) {
			System.out.println("Robot Collided with Drone");
			PlayerRobot pr = (PlayerRobot) this;
			gw.droneColission(pr);
		}
		if (this instanceof NonPlayerRobot && obj instanceof Base) {
			System.out.println("NPR Collided with Base");
			Base base = (Base) obj;
			NonPlayerRobot npr = (NonPlayerRobot) this;
			gw.baseCollide(npr, base);
		}
		if (this instanceof NonPlayerRobot && obj instanceof EnergyStation) {
			System.out.println("NPR Collided with EnergyStation");
			EnergyStation es = (EnergyStation) obj;
			NonPlayerRobot npr = (NonPlayerRobot) this;
			gw.esCollision(es, npr);
		}
		if (this instanceof NonPlayerRobot && obj instanceof Drone) {
			System.out.println("RoNPRbot Collided with Drone");
			NonPlayerRobot npr = (NonPlayerRobot) this;
			gw.droneColission(npr);
		}

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
