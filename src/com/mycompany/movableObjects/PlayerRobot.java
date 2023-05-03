package com.mycompany.movableObjects;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.mycompany.fixedObjects.Base;
import com.mycompany.fixedObjects.EnergyStation;
import com.mycompany.interfaces.IColection;
import com.mycompany.interfaces.ICollider;

public class PlayerRobot extends Robot {
	private static PlayerRobot playerRobot;
	public int width;
	public int height;
	public GameWorld gw;

	private PlayerRobot(int size, double locationX, double locationY) {
		super(size, locationX, locationY, 0, 255, 0, 0);
	}

	public void move(int width, int height, int elapsedTime) {

		super.setHeading(this.getHeading() + this.getSteeringDirection());
		super.move(width, height, elapsedTime);

	}

	public void respawn() {
		this.setColor(255, 0, 0);
		this.setEnergyLevel(10000);
		this.setMaximumSpeed(10);
		this.setDamageLevel(0);
	}

	@Override
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.rgb(this.getRed(), this.getGreen(), this.getBlue()));

		g.fillRect((int) (p.getX() + this.getLocationX() - (this.getSize() / 2)),
				(int) (p.getY() + this.getLocationY() - (this.getSize() / 2)), this.getSize(), this.getSize());
	}

	public static PlayerRobot getPlayerRobot(int size, double locationX, double locationY) {
		if (playerRobot == null) {
			playerRobot = new PlayerRobot(size, locationX, locationY);
		}
		return playerRobot;
	}
}
