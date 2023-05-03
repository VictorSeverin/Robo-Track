package com.mycompany.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.Dialog;

public class Pause extends Command {
	public UITimer timer;
	public GameWorld gw;
	public Game game;

	public Pause(GameWorld gw, UITimer timer, Game game) {
		super("Pause");
		this.timer = timer;
		this.gw = gw;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.pauseGame(timer, game);
		game.pauseGame();
	}
}
