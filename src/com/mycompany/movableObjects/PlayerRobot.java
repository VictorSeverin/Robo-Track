package com.mycompany.movableObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerRobot extends Robot {
    private static PlayerRobot playerRobot;

    private PlayerRobot(int size, double locationX, double locationY) {
        super(size, locationX, locationY, 0);
    }

    @Override
    public void draw(Graphics g, Point p) {
        g.setColor(ColorUtil.rgb(255, 0, 0));
        g.fillRect((int) (p.getX() + this.getLocationX() - (this.getSize() / 2)),
                (int) (p.getY() + this.getLocationY() - (this.getSize() / 2)), this.getSize(),
                this.getSize());
        g.setColor(ColorUtil.rgb(255, 0, 0));
        int radius = (int) Math
                .sqrt((this.getSize() / 2 * this.getSize() / 2) + ((this.getSize() / 2) * (this.getSize() / 2)));
        g.drawArc((int) (p.getX() + this.getLocationX()) - radius, (int) (p.getY() +
                this.getLocationY()) - radius, radius * 2, radius * 2, 0, 360);

    }

    public static PlayerRobot getPlayerRobot(int size, double locationX, double locationY) {
        if (playerRobot == null) {
            playerRobot = new PlayerRobot(size, locationX, locationY);
        }
        return playerRobot;
    }
}
