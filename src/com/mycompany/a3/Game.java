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
import com.codename1.ui.util.UITimer;
import com.mycompany.commands.About;
import com.mycompany.commands.Accelerate;
import com.mycompany.commands.Brake;
import com.mycompany.commands.ChangeStrategy;
import com.mycompany.commands.CollideBase;
import com.mycompany.commands.CollideDrone;
import com.mycompany.commands.CollideEs;
import com.mycompany.commands.CollideNpr;
import com.mycompany.commands.Exit;
import com.mycompany.commands.Help;
import com.mycompany.commands.LeftTurn;
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
		timer.schedule(20, true, this);

		Accelerate acc = new Accelerate(gw);
		Brake brake = new Brake(gw);
		About about = new About();
		ChangeStrategy changeStrategy = new ChangeStrategy(gw);
		CollideBase collideBase = new CollideBase(gw);
		CollideDrone collideDrone = new CollideDrone(gw);
		CollideEs collideEs = new CollideEs(gw);
		CollideNpr collideNpr = new CollideNpr(gw);
		Exit exit = new Exit(gw);
		Help help = new Help(gw);
		LeftTurn leftTurn = new LeftTurn(gw);
		RightTurn rightTurn = new RightTurn(gw);
		Tick tick = new Tick(gw, timer);
		ToggleSound sound = new ToggleSound(gw);

		setLayout(new BorderLayout());
		// Buttons
		Button accButton = new Button("Accelerate");
		Button leftButton = new Button("Left");
		Button chgStrategyButton = new Button("Change Strategy");
		Button brakeButton = new Button("Break");
		Button righButton = new Button("Right");
		Button cldNprButton = new Button("Collide with NPR");
		Button cldBaseButton = new Button("Collide with Base");
		Button cldEsButton = new Button("Collide with Energy Station");
		Button cldDroneButton = new Button("Collide with Drone");
		Button tickButtoon = new Button("Tick");
		Button helpButton = new Button("Help");
		// key Bindings
		this.addKeyListener('a', acc);
		this.addKeyListener('b', brake);
		this.addKeyListener('l', leftTurn);
		this.addKeyListener('r', rightTurn);
		this.addKeyListener('e', collideEs);
		this.addKeyListener('g', collideDrone);
		this.addKeyListener('t', tick);

		// Styles
		accButton.getStyle().setBgTransparency(255);
		leftButton.getStyle().setBgTransparency(255);
		chgStrategyButton.getStyle().setBgTransparency(255);
		righButton.getStyle().setBgTransparency(255);
		cldNprButton.getStyle().setBgTransparency(255);
		cldEsButton.getStyle().setBgTransparency(255);
		cldDroneButton.getStyle().setBgTransparency(255);
		tickButtoon.getStyle().setBgTransparency(255);
		helpButton.getStyle().setBgTransparency(255);
		brakeButton.getStyle().setBgTransparency(255);
		cldBaseButton.getStyle().setBgTransparency(255);
		// bg COlor
		accButton.getStyle().setBgColor(ColorUtil.BLUE);
		leftButton.getStyle().setBgColor(ColorUtil.BLUE);
		chgStrategyButton.getStyle().setBgColor(ColorUtil.BLUE);
		righButton.getStyle().setBgColor(ColorUtil.BLUE);
		cldNprButton.getStyle().setBgColor(ColorUtil.BLUE);
		cldEsButton.getStyle().setBgColor(ColorUtil.BLUE);
		cldDroneButton.getStyle().setBgColor(ColorUtil.BLUE);
		tickButtoon.getStyle().setBgColor(ColorUtil.BLUE);
		helpButton.getStyle().setBgColor(ColorUtil.BLUE);
		brakeButton.getStyle().setBgColor(ColorUtil.BLUE);
		cldBaseButton.getStyle().setBgColor(ColorUtil.BLUE);
		// padding
		accButton.getStyle().setPadding(5, 5, 5, 5);
		leftButton.getStyle().setPadding(5, 5, 5, 5);
		chgStrategyButton.getStyle().setPadding(5, 5, 5, 5);
		righButton.getStyle().setPadding(5, 5, 5, 5);
		brakeButton.getStyle().setPadding(5, 5, 5, 5);
		cldNprButton.getStyle().setPadding(5, 5, 3, 3);
		cldEsButton.getStyle().setPadding(5, 5, 3, 3);
		cldDroneButton.getStyle().setPadding(5, 5, 3, 3);
		tickButtoon.getStyle().setPadding(5, 5, 3, 3);
		helpButton.getStyle().setPadding(5, 5, 0, 0);
		cldBaseButton.getStyle().setPadding(5, 5, 0, 0);

		// border
		accButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		leftButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		chgStrategyButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		righButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		brakeButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		cldNprButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		cldEsButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		cldDroneButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		tickButtoon.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		helpButton.getStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		// text color
		accButton.getStyle().setFgColor(ColorUtil.WHITE);
		leftButton.getStyle().setFgColor(ColorUtil.WHITE);
		chgStrategyButton.getStyle().setFgColor(ColorUtil.WHITE);
		righButton.getStyle().setFgColor(ColorUtil.WHITE);
		brakeButton.getStyle().setFgColor(ColorUtil.WHITE);
		cldNprButton.getStyle().setFgColor(ColorUtil.WHITE);
		cldEsButton.getStyle().setFgColor(ColorUtil.WHITE);
		cldDroneButton.getStyle().setFgColor(ColorUtil.WHITE);
		tickButtoon.getStyle().setFgColor(ColorUtil.WHITE);
		helpButton.getStyle().setFgColor(ColorUtil.WHITE);
		cldBaseButton.getStyle().setFgColor(ColorUtil.WHITE);

		accButton.getStyle().setMarginTop(100);
		brakeButton.getStyle().setMarginTop(100);

		// Event Listeners
		accButton.addActionListener(acc);
		leftButton.addActionListener(leftTurn);
		chgStrategyButton.addActionListener(changeStrategy);
		brakeButton.addActionListener(brake);
		righButton.addActionListener(rightTurn);
		cldNprButton.addActionListener(collideNpr);
		cldBaseButton.addActionListener(collideBase);
		cldEsButton.addActionListener(collideEs);
		cldDroneButton.addActionListener(collideDrone);
		tickButtoon.addActionListener(tick);
		helpButton.addActionListener(sound);
		// Containers
		Container wesContainer = new Container(new BoxLayout(2));
		wesContainer.add(accButton);
		wesContainer.add(leftButton);
		wesContainer.add(chgStrategyButton);

		Container eastContainer = new Container(new BoxLayout(2));
		eastContainer.add(brakeButton);
		eastContainer.add(righButton);

		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		southContainer.add(cldNprButton);
		southContainer.add(cldBaseButton);
		southContainer.add(cldEsButton);
		southContainer.add(cldDroneButton);
		southContainer.add(tickButtoon);

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
	}

}
