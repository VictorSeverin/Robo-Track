package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class FixedObject extends GameObject {
	public FixedObject(int size, double locationX, double locationY, int color) {
		super(size, locationX, locationY, color);
	}

	@Override
	void setLocationX(double x) {
	}

	@Override
	void setLocationY(double y) {
	}

}
