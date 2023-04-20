package com.mycompany.movableObjects;

import com.codename1.charts.models.Point;
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
	private int maximumSpeed;
	private int energyLevel;
	private int energyConsumptionRate;
	private int damageLevel;
	private int lastBaseReached;
	private int maxDamageLevel;
	private int maxEnergyLevel;
	public int width;
	public int height;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param heading   - angle of where the object is headed
	 * @param speed     - speed of the object
	 * 
	 **/
	public Robot(int size, double locationX, double locationY, int speed) {
		super(size, locationX, locationY, ColorUtil.BLUE, 0, speed);
		this.maximumSpeed = 10;
		this.energyLevel = 10000;
		this.damageLevel = 0;
		this.energyConsumptionRate = 1;
		this.lastBaseReached = 0;
		this.steeringDirection = 0;
		this.maxDamageLevel = 50;
		this.maxEnergyLevel = 1000;
	}

	public void move(int width, int height, int elapsedTime) {
		if (this.energyLevel > 0 && this.damageLevel < this.maxDamageLevel && this.getSpeed() > 0) {
			this.energyLevel -= this.energyConsumptionRate;
			super.move(width, height, elapsedTime);
		}
	}

	@Override
	public void draw(Graphics g, Point p) {
		// g.setColor(ColorUtil.YELLOW);
		// g.fillRect((int) (p.getX() + this.getLocationX() - this.getSize() / 2),
		// (int) (p.getY() + this.getLocationY()) - this.getSize() / 2, this.getSize(),
		// this.getSize());
		// g.setColor(ColorUtil.rgb(255, 0, 0));
		// g.drawArc((int) (p.getX() + this.getLocationX()) - (this.getSize() / 2),
		// (int) (p.getY() +
		// this.getLocationY()) - (this.getSize() / 2), this.getSize(), this.getSize(),
		// 0, 360);

	}

	/**
	 * @param degrees - number of degrees to turn left/right
	 */
	public void turn(int degrees) {
		this.steeringDirection = degrees;
	}

	public void setMaximumSpeed(int speed) {
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
