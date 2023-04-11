package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideEs extends Command {
    private GameWorld gw;

    public CollideEs(GameWorld gw) {
        super("Collide Energy Station");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.esCollision();
    }
}
