package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.Dialog;

public class Exit extends Command {
	private GameWorld gw;

	public Exit(GameWorld gw) {
		super("Exit");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Command cOk = new Command("Confirm");
		Command cCancel = new Command("Dunno");
		Command[] cmds = new Command[] { cOk, cCancel };
		Command c = Dialog.show("Exit", "Are you sure you want to exit?", cmds);
		if (c == cOk) {
			gw.exitGame();
		}
	}
}
