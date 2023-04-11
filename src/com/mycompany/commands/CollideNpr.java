package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideNpr extends Command {
    private GameWorld gw;

    public CollideNpr(GameWorld gw) {
        super("Collide with NPR");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.robotCollision();
    }
}
