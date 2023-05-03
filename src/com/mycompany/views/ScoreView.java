package com.mycompany.views;

import java.util.Observer;
import java.util.Observable;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.a3.GameWorld;

public class ScoreView extends Container implements Observer {
	private Label timeLabel;
	private Label livesLabel;
	private Label lastBaseLabel;
	private Label energyLevelLabel;
	private Label damageLabel;
	private Label soundLabel;

	public ScoreView() {
		timeLabel = new Label("Time: 00000");
		livesLabel = new Label("Lives left: 000");
		lastBaseLabel = new Label("Labst Base Reached: 000");
		energyLevelLabel = new Label("Energy Level: 100");
		damageLabel = new Label("Damage Level: 100");
		soundLabel = new Label("Sound: OFF");

		timeLabel.getStyle().setFgColor(ColorUtil.BLUE);
		livesLabel.getStyle().setFgColor(ColorUtil.BLUE);
		lastBaseLabel.getStyle().setFgColor(ColorUtil.BLUE);
		energyLevelLabel.getStyle().setFgColor(ColorUtil.BLUE);
		damageLabel.getStyle().setFgColor(ColorUtil.BLUE);
		soundLabel.getStyle().setFgColor(ColorUtil.BLUE);

		add(timeLabel);
		add(livesLabel);
		add(lastBaseLabel);
		add(energyLevelLabel);
		add(damageLabel);
		add(soundLabel);
	}

	public void update(Observable o, Object data) {
		GameWorld gw = (GameWorld) data;
		timeLabel.setText("Time: " + gw.getTime() + "  ");
		livesLabel.setText("Lives Left: " + gw.getLivesLeft() + "  ");
		lastBaseLabel.setText("Player Base Reached: " + gw.getRobot().getLastBaseReached() + "  ");
		energyLevelLabel.setText("Player Energy Level: " + gw.getRobot().getEnergyLevel() + "  ");
		damageLabel.setText("Player Damage Level: " + gw.getRobot().getDamageLevel() + "  ");
		soundLabel.setText("Sound: " + gw.getSound() + "  ");
		this.repaint();
	}
}
