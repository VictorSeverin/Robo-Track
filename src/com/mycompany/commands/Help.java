package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.Dialog;

public class Help extends Command {
	private GameWorld gw;

	public Help(GameWorld gw) {
		super("Help");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Dialog.show("Help", "l-steer left, r-steer right, a-accelerate, b-brake", "OK", "Cancel");

	}
}
