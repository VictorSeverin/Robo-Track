package com.mycompany.fixedObjects;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.FixedObject;
import com.mycompany.interfaces.ICollider;

public class EnergyStation extends FixedObject {

	private int capacity;
	private int width;
	private int height;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param color     - color of the object
	 **/
	public EnergyStation(int size, double locationX, double locationY, int width, int height) {
		super(size, locationX, locationY, ColorUtil.GREEN);
		this.capacity = size / 2;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean contains(Point pRel, Point pComp) {
		double px = pRel.getX(); // pointer location relative to
		double py = pRel.getY(); // parent’s origin
		double xLoc = this.getLocationX();// shape location relative
		double yLoc = this.getLocationY();// to parent’s origin

		if (px >= xLoc - this.getSize() / 2 && px <= (xLoc + this.getSize()) - this.getSize() / 2
				&& py >= yLoc - this.getSize() / 2 && py <= (yLoc + this.getSize()) - this.getSize() / 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g, Point p) {
		if (this.isSelected()) {
			g.setColor(ColorUtil.GREEN);
			g.drawArc((int) (p.getX() + this.getLocationX()) - (this.getSize() / 2), (int) (p.getY() +
					this.getLocationY()) - (this.getSize() / 2), this.getSize(), this.getSize(), 0, 360);
			g.drawString(Integer.toString(this.getCapacity()),
					(int) p.getX() + (int) this.getLocationX() - 10,
					(int) p.getY() + (int) this.getLocationY() - 10);
		} else {
			g.setColor(ColorUtil.GREEN);
			g.fillArc((int) (p.getX() + this.getLocationX()) - (this.getSize() / 2), (int) (p.getY() +
					this.getLocationY()) - (this.getSize() / 2), this.getSize(), this.getSize(), 0, 360);
			g.setColor(ColorUtil.rgb(255, 0, 0));
			g.drawString(Integer.toString(this.getCapacity()),
					(int) p.getX() + (int) this.getLocationX() - 10,
					(int) p.getY() + (int) this.getLocationY() - 10);
		}
	}

	public int getCapacity() {
		return capacity;
	}

	public void drain() {
		this.capacity = 0;
	}

	@Override
	public String toString() {
		return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=["
				+ this.getColor() + "] size=" + this.getSize() + " capacity="
				+ this.getCapacity();
	}

}
