package com.mycompany.movableObjects;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.MovableObject;
import com.mycompany.fixedObjects.Base;
import com.mycompany.fixedObjects.EnergyStation;
import com.mycompany.interfaces.ICollider;
import com.mycompany.interfaces.iSteerable;

public class Robot extends MovableObject implements iSteerable {
	private int steeringDirection;
	private double maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;
	private int maxDamageLevel;
	private int maxEnergyLevel;
	public int width;
	public int height;
	private int maxSteeringAngle = 40;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param heading   - angle of where the object is headed
	 * @param speed     - speed of the object
	 * 
	 **/
	public Robot(int size, double locationX, double locationY, int speed, int red, int green, int blue) {
		super(size, locationX, locationY, 0, speed, red, green, blue);
		this.maximumSpeed = 10;
		this.energyLevel = 10000;
		this.damageLevel = 0;
		this.energyConsumptionRate = 1;
		this.lastBaseReached = 0;
		this.steeringDirection = 0;
		this.maxDamageLevel = 70;
		this.maxEnergyLevel = 1000;
	}

	public void move(int width, int height, int elapsedTime) {
		if (this.energyLevel > 0 && this.damageLevel < this.maxDamageLevel && this.getSpeed() > 0) {
			this.energyLevel -= this.energyConsumptionRate;
			// super.move(width, height, elapsedTime);
		}
	}

	@Override
	public void draw(Graphics g, Point p, Point pCmpRelScrn) {

	}

	/**
	 * @param degrees - number of degrees to turn left/right
	 */
	public void turn(int degrees) {
		if (degrees > maxSteeringAngle) {
			degrees = maxSteeringAngle;
		} else if (degrees <= -maxSteeringAngle) {
			degrees = -maxSteeringAngle;
		}
		this.setHeading(this.getHeading() + degrees);
		this.setHeading(this.getHeading() % 360);
	}

	public void setMaximumSpeed(double speed) {
		this.maximumSpeed = speed;
	}

	public void setBase(int i) {
		this.lastBaseReached = i;
	}

	public void setSteeringDirection(int direction) {
		this.steeringDirection = direction;
	}

	public int getSteeringDirection() {
		return steeringDirection;
	}

	public int getMaxEnergyLevel() {
		return maxEnergyLevel;
	}

	public int getMaxDamageLevel() {
		return maxDamageLevel;
	}

	public double getMaximumSpeed() {
		return this.maximumSpeed;
	}

	public int getEnergyLevel() {
		return this.energyLevel;
	}

	public int getEnergyConsumptionRate() {
		return this.energyConsumptionRate;
	}

	public int getDamageLevel() {
		return this.damageLevel;
	}

	public int getLastBaseReached() {
		return this.lastBaseReached;
	}

	public void setSpeed(int speed) {
		super.setSpeed(speed);
	}

	public void setDamageLevel(int damage) {
		this.damageLevel = damage;
	}

	public void setEnergyLevel(int amount) {
		this.energyLevel = amount;
	}

	@Override
	public String toString() {
		return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=[" + this.getColor() + "] heading="
				+ this.getHeading() + " speed=" + this.getSpeed() + " size=" + this.getSize() + " maxSpeed="
				+ this.getMaximumSpeed() + " steeringDirection=" + this.getSteeringDirection() + " energyLevel="
				+ this.getEnergyLevel() + " damageLevel= " + this.getDamageLevel();
	}

}
