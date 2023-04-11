package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.mycompany.interfaces.IDrawable;

public abstract class GameObject implements IDrawable {
	private int size;
	private double locationX;
	private double locationY;
	private int color;

	GameObject(int size, double locationX, double locationY, int color) {
		this.size = size;
		this.locationX = locationX;
		this.locationY = locationY;
		this.color = color;
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
