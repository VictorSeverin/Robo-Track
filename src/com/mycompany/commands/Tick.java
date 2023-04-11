package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class Tick extends Command {
    private GameWorld gw;

    public Tick(GameWorld gw) {
        super("Tick");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        gw.tickClock();
    }
}
