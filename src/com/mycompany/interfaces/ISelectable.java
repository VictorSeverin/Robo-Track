package com.mycompany.interfaces;

import com.codename1.ui.geom.Point;
import com.codename1.ui.Graphics;

public interface ISelectable {
	public void setSelected(boolean b);

	public boolean isSelected();

	public boolean contains(Point pRel, Point pComp);

	public void draw(Graphics g, Point p);

}
