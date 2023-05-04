package com.mycompany.movableObjects;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
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
	private Point topLeft, topRight, bottomLeft, bottomRight;
	private Transform myRotation, myTranslation, myScale;

	private PlayerRobot(int size, double locationX, double locationY) {
		super(size, locationX, locationY, 0, 255, 0, 0);
		topLeft = new Point(-size / 2, size / 2);
		topRight = new Point(size / 2, size / 2);
		bottomLeft = new Point(-size / 2, -size / 2);
		bottomRight = new Point(size / 2, -size / 2);
		myRotation = Transform.makeIdentity();
		myTranslation = Transform.makeIdentity();
		myScale = Transform.makeIdentity();
	}

	public void rotate(float degrees) {
		myRotation.rotate((float) Math.toRadians(degrees), 0, 0);
	}

	public void translate(float tx, float ty) {
		myTranslation.translate(tx, ty);
	}

	public void scale(float sx, float sy) {
		myScale.scale(sx, sy);
	}

	public void resetTransform() {
		myRotation.setIdentity();
		myTranslation.setIdentity();
		myScale.setIdentity();
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
	public void draw(Graphics g, Point p, Point pCmpRelScrn) {
		g.setColor(ColorUtil.rgb(this.getRed(), this.getGreen(), this.getBlue()));
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		gXform.translate(pCmpRelScrn.getX(), pCmpRelScrn.getY());
		gXform.translate(myTranslation.getTranslateX(),
				myTranslation.getTranslateY());
		gXform.concatenate(myRotation);
		gXform.scale(myScale.getScaleX(), myScale.getScaleY());
		gXform.translate(-pCmpRelScrn.getX(), -pCmpRelScrn.getY());
		g.setTransform(gXform);
		g.fillRect(p.getX(),
				p.getY(), this.getSize(), this.getSize());
	}

	public static PlayerRobot getPlayerRobot(int size, double locationX, double locationY) {
		if (playerRobot == null) {
			playerRobot = new PlayerRobot(size, locationX, locationY);
		}
		return playerRobot;
	}
}
