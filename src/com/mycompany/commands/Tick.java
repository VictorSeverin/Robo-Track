package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.GameWorld;

public class Tick extends Command {
	private GameWorld gw;
	public UITimer timer = null;

	public Tick(GameWorld gw, UITimer timer) {
		super("Tick");
		this.gw = gw;
		this.timer = timer;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.tickClock(timer);
	}
}
