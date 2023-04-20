package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Timer;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Label;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.UITimer;
import com.mycompany.commands.About;
import com.mycompany.commands.Accelerate;
import com.mycompany.commands.Brake;
import com.mycompany.commands.ChangeStrategy;
import com.mycompany.commands.Exit;
import com.mycompany.commands.Help;
import com.mycompany.commands.LeftTurn;
import com.mycompany.commands.Pause;
import com.mycompany.commands.Position;
import com.mycompany.commands.RightTurn;
import com.mycompany.commands.Tick;
import com.mycompany.commands.ToggleSound;
import com.mycompany.views.MapView;
import com.mycompany.views.ScoreView;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Button;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Dialog;

import java.io.IOException;
import java.lang.String;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	UITimer timer = new UITimer(this);

	@Override
	public void run() {
		gw.tickClock(timer);
	}

	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);

		Accelerate acc = new Accelerate(gw);
		Brake brake = new Brake(gw);
		About about = new About();
		ChangeStrategy changeStrategy = new ChangeStrategy(gw);
		Exit exit = new Exit(gw);
		Help help = new Help(gw);
		LeftTurn leftTurn = new LeftTurn(gw);
		RightTurn rightTurn = new RightTurn(gw);
		Tick tick = new Tick(gw, timer);
		ToggleSound sound = new ToggleSound(gw);
		Pause pause = new Pause(gw);
		Position position = new Position(gw);

		setLayout(new BorderLayout());
		// Buttons
		Button accButton = new Button("Accelerate");
		Button leftButton = new Button("Left");
		Button chgStrategyButton = new Button("Change Strategy");
		Button brakeButton = new Button("Break");
		Button righButton = new Button("Right");
		Button tickButtoon = new Button("Tick");
		Button helpButton = new Button("Help");
		Button pauseButton = new Button("Pause");
		Button positionButton = new Button("position");
		// key Bindings
		this.addKeyListener('a', acc);
		this.addKeyListener('b', brake);
		this.addKeyListener('l', leftTurn);
		this.addKeyListener('r', rightTurn);
		this.addKeyListener('t', tick);
		this.addKeyListener('p', pause);

		// Styles
		accButton.getStyle().setBgTransparency(255);
		leftButton.getStyle().setBgTransparency(255);
		chgStrategyButton.getStyle().setBgTransparency(255);
		righButton.getStyle().setBgTransparency(255);
		tickButtoon.getStyle().setBgTransparency(255);
		helpButton.getStyle().setBgTransparency(255);
		brakeButton.getStyle().setBgTransparency(255);
		pauseButton.getStyle().setBgTransparency(255);
		positionButton.getStyle().setBgTransparency(255);
		// bg COlor
		accButton.getStyle().setBgColor(ColorUtil.BLUE);
		leftButton.getStyle().setBgColor(ColorUtil.BLUE);
		chgStrategyButton.getStyle().setBgColor(ColorUtil.BLUE);
		righButton.getStyle().setBgColor(ColorUtil.BLUE);
		tickButtoon.getStyle().setBgColor(ColorUtil.BLUE);
		helpButton.getStyle().setBgColor(ColorUtil.BLUE);
		brakeButton.getStyle().setBgColor(ColorUtil.BLUE);
		pauseButton.getStyle().setBgColor(ColorUtil.BLUE);
		positionButton.getStyle().setBgColor(ColorUtil.BLUE);
		// padding
		accButton.getStyle().setPadding(5, 5, 5, 5);
		leftButton.getStyle().setPadding(5, 5, 5, 5);
		chgStrategyButton.getStyle().setPadding(5, 5, 5, 5);
		righButton.getStyle().setPadding(5, 5, 5, 5);
		brakeButton.getStyle().setPadding(5, 5, 5, 5);
		tickButtoon.getStyle().setPadding(5, 5, 3, 3);
		helpButton.getStyle().setPadding(5, 5, 0, 0);
		pauseButton.getStyle().setPadding(5, 5, 3, 3);
		positionButton.getStyle().setPadding(5, 5, 3, 3);

		// border
		accButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		leftButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		chgStrategyButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		righButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		brakeButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		tickButtoon.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		helpButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		pauseButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		positionButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		// text color
		accButton.getStyle().setFgColor(ColorUtil.WHITE);
		leftButton.getStyle().setFgColor(ColorUtil.WHITE);
		chgStrategyButton.getStyle().setFgColor(ColorUtil.WHITE);
		righButton.getStyle().setFgColor(ColorUtil.WHITE);
		brakeButton.getStyle().setFgColor(ColorUtil.WHITE);
		tickButtoon.getStyle().setFgColor(ColorUtil.WHITE);
		helpButton.getStyle().setFgColor(ColorUtil.WHITE);
		pauseButton.getStyle().setFgColor(ColorUtil.WHITE);
		positionButton.getStyle().setFgColor(ColorUtil.WHITE);

		accButton.getStyle().setMarginTop(100);
		brakeButton.getStyle().setMarginTop(100);

		// Event Listeners
		accButton.addActionListener(acc);
		leftButton.addActionListener(leftTurn);
		chgStrategyButton.addActionListener(changeStrategy);
		brakeButton.addActionListener(brake);
		righButton.addActionListener(rightTurn);
		tickButtoon.addActionListener(tick);
		helpButton.addActionListener(sound);
		pauseButton.addActionListener(pause);
		positionButton.addActionListener(position);
		// Containers
		Container wesContainer = new Container(new BoxLayout(2));
		wesContainer.add(accButton);
		wesContainer.add(leftButton);
		wesContainer.add(chgStrategyButton);

		Container eastContainer = new Container(new BoxLayout(2));
		eastContainer.add(brakeButton);
		eastContainer.add(righButton);

		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		southContainer.add(positionButton);
		southContainer.add(pauseButton);

		Container topContainer = new Container(new FlowLayout(Component.CENTER));
		topContainer.add(sv);

		// Container Styles
		wesContainer.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		southContainer.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		eastContainer.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));

		add(BorderLayout.NORTH, topContainer);// add them to the display
		add(BorderLayout.WEST, wesContainer);
		add(BorderLayout.EAST, eastContainer);
		add(BorderLayout.SOUTH, southContainer);
		add(BorderLayout.CENTER, mv);
		// Toolbar
		Toolbar topToolbar = new Toolbar();
		setToolbar(topToolbar);
		topToolbar.setTitleComponent(new Label("Robot-track Game"));
		Command sideMenuItem = new Command("Side Menu");
		topToolbar.addCommandToLeftSideMenu(sideMenuItem);

		CheckBox soundCheckBox = new CheckBox("Sound");
		soundCheckBox.setOppositeSide(false);
		soundCheckBox.addActionListener(sound);
		soundCheckBox.getAllStyles().setFgColor(ColorUtil.WHITE);
		topToolbar.addCommandToRightBar(help);
		topToolbar.addCommandToSideMenu(acc);
		topToolbar.addCommandToSideMenu(about);
		topToolbar.addCommandToSideMenu(exit);
		topToolbar.addComponentToLeftSideMenu(soundCheckBox);
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		this.show();
		gw.init();
		gw.createSounds();
		this.revalidate();
		timer.schedule(20, true, this);
	}

}
