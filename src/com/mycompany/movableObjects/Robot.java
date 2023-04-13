package com.mycompany.movableObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.MovableObject;
import com.mycompany.interfaces.iSteerable;

public class Robot extends MovableObject implements iSteerable {
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;
	private int maxDamageLevel;
	private static Robot theRobot;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param heading   - angle of where the object is headed
	 * @param speed     - speed of the object
	 * 
	 **/
	public Robot(int size, double locationX, double locationY) {
		super(size, locationX, locationY, ColorUtil.rgb(255, 0, 0), 90, 0);
		this.maximumSpeed = 50;
		this.energyLevel = 100;
		this.damageLevel = 0;
		this.energyConsumptionRate = 5;
		this.lastBaseReached = 1;
		this.steeringDirection = 90;
		this.maxDamageLevel = 50;
	}

	@Override
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.YELLOW);
		g.fillRect((int) (p.getX() + this.getLocationX()), (int) (p.getY() + this.getLocationY()), this.getSize(),
				this.getSize());

	}

	// singleton pattern
	public static Robot getRobot() {
		if (theRobot == null) {
			theRobot = new Robot(0, 0, 0);
		}
		return theRobot;
	}

	public void move() {
		if (this.energyLevel > 0 && this.damageLevel < this.maxDamageLevel && this.getSpeed() > 0) {
			super.setHeading(this.getHeading() + this.steeringDirection);
		}
		this.energyLevel -= this.energyConsumptionRate;
		super.move();
	}

	/**
	 * @param degrees - number of degrees to turn left/right
	 */
	public void turn(int degrees) {
		this.steeringDirection += degrees;
	}

	public void setMaximumSpeed(int speed) {
		this.maximumSpeed = speed;
	}

	public void setBase(int i) {
		this.lastBaseReached = i;
	}

	public int getSteeringDirection() {
		return steeringDirection;
	}

	public int getMaxDamageLevel() {
		return maxDamageLevel;
	}

	public int getMaximumSpeed() {
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
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=["
				+ this.getColor() + "] heading=" + this.getHeading() + " speed=" + this.getSpeed() + " size="
				+ this.getSize() + " maxSpeed=" + this.getMaximumSpeed() + " steeringDirection="
				+ this.getSteeringDirection()
				+ " energyLevel=" + this.getEnergyLevel() + " damageLevel= " + this.getDamageLevel();
	}

}
