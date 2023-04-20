package com.mycompany.movableObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.mycompany.fixedObjects.Base;
import com.mycompany.fixedObjects.EnergyStation;
import com.mycompany.interfaces.IColection;
import com.mycompany.interfaces.ICollider;

public class PlayerRobot extends Robot {
    private static PlayerRobot playerRobot;
    public int width;
    public int height;
    public GameWorld gw;

    private PlayerRobot(int size, double locationX, double locationY) {
        super(size, locationX, locationY, 0);
    }

    public void move(int width, int height, int elapsedTime) {
        super.setHeading(this.getHeading() + this.getSteeringDirection());
        super.move(width, height, elapsedTime);

    }

    @Override
    public void draw(Graphics g, Point p) {
        g.setColor(ColorUtil.rgb(255, 0, 0));
        g.drawRect((int) (p.getX() + this.getLocationX() - (this.getSize() / 2)),
                (int) (p.getY() + this.getLocationY() - (this.getSize() / 2)),
                this.getSize(),
                this.getSize());
        g.setColor(ColorUtil.rgb(255, 0, 0));
        // int radius = (int) Math
        // .sqrt((this.getSize() / 2 * this.getSize() / 2) + ((this.getSize() / 2) *
        // (this.getSize() / 2)));
        // g.drawArc((int) (p.getX() + this.getLocationX()) - radius, (int) (p.getY() +
        // this.getLocationY()) - radius, radius * 2, radius * 2, 0, 360);
        g.drawString(
                Double.toString(Math.round(this.getLocationX())) + " "
                        + Double.toString(Math.round(this.getLocationY())),
                (int) p.getX() + (int) this.getLocationX() - 10,
                (int) p.getY() + (int) this.getLocationY() - 20);
    }

    public static PlayerRobot getPlayerRobot(int size, double locationX, double locationY) {
        if (playerRobot == null) {
            playerRobot = new PlayerRobot(size, locationX, locationY);
        }
        return playerRobot;
    }
}
