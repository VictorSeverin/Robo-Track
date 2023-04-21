package com.mycompany.fixedObjects;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.FixedObject;
import com.mycompany.interfaces.ICollider;

public class Base extends FixedObject {
	private int sequenceNumber;
	private int width;
	private int height;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param color     - color of the object specifically
	 **/
	public Base(int size, double locationX, double locationY, int sequenceNumber, int width, int height) {
		super(size, locationX, locationY, ColorUtil.CYAN);
		this.sequenceNumber = sequenceNumber;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the sequence number of the given base
	 **/
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	@Override
	public boolean contains(Point pRel, Point pComp) {
		double px = pRel.getX(); // pointer location relative to
		double py = pRel.getY(); // parent’s origin
		double xLoc = this.getLocationX();// shape location relative
		double yLoc = this.getLocationY();// to parent’s origin
		System.out.println("X: " + xLoc + "Y: " + yLoc);
		if (px >= xLoc - this.getSize() / 2 && px <= (xLoc + this.getSize()) - this.getSize() / 2
				&& py >= yLoc - this.getSize() / 2 && py <= (yLoc + this.getSize()) - this.getSize() / 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.CYAN);
		int[] xCoordinates = {
				(int) p.getX() + (int) this.getLocationX() - (this.getSize() / 2),
				((int) p.getX() + (int) this.getLocationX()),
				((int) p.getX() + (int) this.getLocationX()) + (this.getSize() / 2) };
		int[] yCoordinates = {
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize() / 2),
				((int) p.getY() + (int) this.getLocationY()) + (this.getSize() / 2),
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize() / 2) };
		if (this.isSelected()) {
			g.drawPolygon(xCoordinates, yCoordinates, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(Integer.toString(this.getSequenceNumber()),
					(int) p.getX() + (int) this.getLocationX() - 10,
					(int) p.getY() + (int) this.getLocationY() - 20);
		} else {
			g.fillPolygon(xCoordinates, yCoordinates, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawString(Integer.toString(this.getSequenceNumber()),
					(int) p.getX() + (int) this.getLocationX() - 10,
					(int) p.getY() + (int) this.getLocationY() - 20);
		}
	}

	@Override
	public void setColor(int color) {
	}

	@Override
	public String toString() {
		return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=["
				+ this.getColor()
				+ " size="
				+ this.getSize() + " seqNum= " + this.sequenceNumber;
	}
}
