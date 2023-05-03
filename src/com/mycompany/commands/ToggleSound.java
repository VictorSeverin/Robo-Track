package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.CheckBox;

public class ToggleSound extends Command {
	private GameWorld gw;

	public ToggleSound(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.toggleSound();
	}
}
