package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class CollideBase extends Command {
    private GameWorld gw;

    public CollideBase(GameWorld gw) {
        super("Base Collide");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.baseCollide();
    }
}
