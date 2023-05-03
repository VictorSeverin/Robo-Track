package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class RightTurn extends Command {
	private GameWorld gw;

	public RightTurn(GameWorld gw) {
		super("Right Turn");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.steerRight();
	}
}
