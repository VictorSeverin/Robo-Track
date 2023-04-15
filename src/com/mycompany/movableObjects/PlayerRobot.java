package com.mycompany.movableObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerRobot extends Robot {
    private static PlayerRobot playerRobot;

    private PlayerRobot(int size, double locationX, double locationY) {
        super(size, locationX, locationY);
    }

    @Override
    public void draw(Graphics g, Point p) {
        g.setColor(ColorUtil.YELLOW);
        g.fillRect((int) (p.getX() + this.getLocationX() - (this.getSize() / 2)),
                (int) (p.getY() + this.getLocationY() - (this.getSize() / 2)), this.getSize(),
                this.getSize());

    }

    public static PlayerRobot getPlayerRobot(int size, double locationX, double locationY) {
        if (playerRobot == null) {
            playerRobot = new PlayerRobot(size, locationX, locationY);
        }
        return playerRobot;
    }
}
