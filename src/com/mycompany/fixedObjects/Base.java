package com.mycompany.fixedObjects;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.mycompany.a3.FixedObject;
import com.mycompany.interfaces.ICollider;

import subObjects.BaseHead;

public class Base extends FixedObject {
	private int sequenceNumber;
	private int width;
	private int height;
	private Point top, bottomLeft, bottomRight;
	BaseHead baseHead;

	/**
	 * @param size      - the size of the object
	 * @param locationX - location of the object on the X axis
	 * @param locationY - location of the object on the Y axis
	 * @param color     - color of the object specifically
	 **/
	public Base(int size, double locationX, double locationY, int sequenceNumber, int width, int height) {
		super(size, locationX, locationY, 36, 224, 227);
		this.sequenceNumber = sequenceNumber;
		this.width = width;
		this.height = height;
		top = new Point(0, size / 2);
		bottomLeft = new Point(-size / 2, -size / 2);
		bottomRight = new Point(size / 2, -size / 2);
		translate((float) locationX, (float) locationY);
		baseHead = new BaseHead(top, bottomLeft, bottomRight);
		baseHead.translate((float) size / 2, size + 10);
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
		if (px >= xLoc - this.getSize() / 2 && px <= (xLoc + this.getSize()) - this.getSize() / 2
				&& py >= yLoc - this.getSize() / 2 && py <= (yLoc + this.getSize()) - this.getSize() / 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
		g.setColor(ColorUtil.rgb(this.getRed(), this.getGreen(), this.getBlue()));
		Transform gXform = Transform.makeIdentity();
		g.getTransform(gXform);
		Transform copy = gXform.copy();
		gXform.translate(pCmpRelScrn.getX(), pCmpRelScrn.getY());
		gXform.translate(getTranslate().getTranslateX(),
				getTranslate().getTranslateY());
		gXform.concatenate(getRotation());
		gXform.scale(getScale().getScaleX(), getScale().getScaleY());
		gXform.translate(-pCmpRelScrn.getX(), -pCmpRelScrn.getY());
		g.setTransform(gXform);
		g.fillRect(pCmpRelPrnt.getX(),
				pCmpRelPrnt.getY(), this.getSize(), this.getSize());
		baseHead.draw(g, pCmpRelPrnt, pCmpRelScrn);
		g.setTransform(copy);
	}

	@Override
	public String toString() {
		return "loc=" + Math.round(this.getLocationX() * 10.0) / 10.0 + ","
				+ Math.round(this.getLocationY() * 10.0) / 10.0 + " color=[" + this.getColor() + " size="
				+ this.getSize() + " seqNum= " + this.sequenceNumber;
	}
}
