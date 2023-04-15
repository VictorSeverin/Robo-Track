package com.mycompany.movableObjects;

public class PlayerRobot extends Robot {
    private static PlayerRobot playerRobot;

    private PlayerRobot(int size, double locationX, double locationY) {
        super(size, locationX, locationY);
    }

    public static PlayerRobot getPlayerRobot(int size, double locationX, double locationY) {
        if (playerRobot == null) {
            playerRobot = new PlayerRobot(size, locationX, locationY);
        }
        return playerRobot;
    }
}
