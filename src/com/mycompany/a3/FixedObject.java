package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.interfaces.ISelectable;

public abstract class FixedObject extends GameObject implements ISelectable {
	private boolean isSelected = false;

	public FixedObject(int size, double locationX, double locationY, int color) {
		super(size, locationX, locationY, color);
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean b) {
		this.isSelected = b;
	}

	@Override
	void setLocationX(double x) {
	}

	@Override
	void setLocationY(double y) {
	}

}
