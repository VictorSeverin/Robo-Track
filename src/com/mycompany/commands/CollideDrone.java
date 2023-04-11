package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideDrone extends Command {
    private GameWorld gw;

    public CollideDrone(GameWorld gw) {
        super("Collide Drone");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.droneColission();
    }
}
