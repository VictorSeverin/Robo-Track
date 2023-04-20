package com.mycompany.fixedObjects;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.FixedObject;
import com.mycompany.interfaces.ICollider;

public class Base extends FixedObject {
	private int sequenceNumber;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param color     - color of the object specifically
	 **/
	public Base(int size, double locationX, double locationY, int sequenceNumber) {
		super(size, locationX, locationY, ColorUtil.CYAN);
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the sequence number of the given base
	 **/
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	@Override
	public void handleCollision(ICollider obj) {
	}

	@Override
	public void draw(Graphics g, Point p) {
		g.setColor(ColorUtil.CYAN);
		int[] xCoordinates = {
				(int) p.getX() + (int) this.getLocationX() - (this.getSize()),
				((int) p.getX() + (int) this.getLocationX()),
				((int) p.getX() + (int) this.getLocationX()) + (this.getSize()) };
		int[] yCoordinates = {
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize()),
				((int) p.getY() + (int) this.getLocationY()) + (this.getSize()),
				((int) p.getY() + (int) this.getLocationY()) - (this.getSize()) };
		g.drawPolygon(xCoordinates, yCoordinates, 3);
		g.setColor(ColorUtil.rgb(255, 0, 0));
		int radius = (int) Math.sqrt((this.getSize() * this.getSize()) + ((this.getSize()) * (this.getSize())));
		g.drawArc((int) (p.getX() + this.getLocationX()) - radius, (int) (p.getY() +
				this.getLocationY()) - radius, radius * 2, radius * 2, 0, 360);
		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(this.getSequenceNumber()),
				(int) p.getX() + (int) this.getLocationX() - 10,
				(int) p.getY() + (int) this.getLocationY() - 20);
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
