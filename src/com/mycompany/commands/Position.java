package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.Dialog;

public class Position extends Command {
	private GameWorld gw;

	public Position(GameWorld gw) {
		super("Position");
		this.gw = gw;

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (gw.getPositionClicked()) {
			gw.setPosition(false);
		} else {
			gw.setPosition(true);
		}
	}
}
