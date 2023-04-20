package com.mycompany.interfaces;

import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameWorld;

public interface ICollider {
	public void handleCollision(ICollider otherObject, GameWorld gw);

	public boolean collidesWith(ICollider otherObject);
}
