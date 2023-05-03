package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.interfaces.ISelectable;

public abstract class FixedObject extends GameObject implements ISelectable {
	private boolean isSelected = false;

	public FixedObject(int size, double locationX, double locationY, int red, int green, int blue) {
		super(size, locationX, locationY, red, green, blue);
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean b) {
		this.isSelected = b;
	}

	public void setLocationX(int location) {
		super.setLocationX(location);
	}

	public void setLocationY(int location) {
		super.setLocationY(location);
	}

}
