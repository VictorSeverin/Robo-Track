package com.mycompany.interfaces;

import com.mycompany.a3.GameWorld;
import com.mycompany.movableObjects.NonPlayerRobot;

public interface IStrategy {
	public String getName();

	public void apply(GameWorld gw, NonPlayerRobot npr);
}
