package com.mycompany.a3;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

import com.codename1.charts.util.ColorUtil;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.util.UITimer;
import com.mycompany.fixedObjects.Base;
import com.mycompany.fixedObjects.EnergyStation;
import com.mycompany.interfaces.ICollider;
import com.mycompany.interfaces.IIterator;
import com.mycompany.movableObjects.Drone;
import com.mycompany.movableObjects.NonPlayerRobot;
import com.mycompany.movableObjects.Robot;
import com.mycompany.strategies.ChaseRobot;
import com.mycompany.strategies.NextBase;
import com.mycompany.movableObjects.PlayerRobot;
import com.codename1.ui.Command;

public class GameWorld extends Observable {
	private GameCollection objects;
	private int clockTime;
	private int remainingLives;
	private boolean sound;
	private int height;
	private int width;
	private Sound prWithDroneSound;
	private Sound prWithBaseSound;
	private Sound prWithEsSound;
	private Sound prWithNprSound;
	private Sound robotExp;
	private BGSound bSound;
	private boolean setPosition;
	private boolean pauseGame;
	public Random rand = new Random();
	private UITimer timer;
	Game game;

	public GameWorld() {
		this.objects = new GameCollection();
		this.clockTime = 0;
		this.remainingLives = 3;
		this.sound = false;
		this.pauseGame = false;
		this.setPosition = false;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setPosition(boolean b) {
		this.setPosition = b;
	}

	public boolean getPositionClicked() {
		return this.setPosition;
	}

	public void createSounds() {
		bSound = new BGSound("bgMusic.wav", this);
		prWithBaseSound = new Sound("baseHit.wav");
		prWithDroneSound = new Sound("plane.wav");
		prWithEsSound = new Sound("electricity.wav");
		prWithNprSound = new Sound("robotCrash.wav");
		robotExp = new Sound("explosion.wav");
		if (getSoundBool()) {
			bSound.run();
		}
	}

	public GameCollection getObjects() {
		return objects;
	}

	public int getTime() {
		return clockTime;
	}

	public int getLivesLeft() {
		return remainingLives;
	}

	public void toggleSound() {
		if (this.sound) {
			this.sound = false;
			bSound.pause();
		} else {
			this.sound = true;
			bSound.play();
		}
		setChanged();
		notifyObservers(this);
	}

	public String getSound() {
		if (this.sound == true) {
			return "ON";
		} else {
			return "OFF";
		}
	}

	public boolean getSoundBool() {
		return this.sound;
	}

	public void exitGame() {
		System.exit(0);
	}

	public boolean isPaused() {
		return pauseGame;
	}

	public void setPause(boolean flag) {
		this.pauseGame = flag;
	}

	public void changeStrategy() {
		IIterator iter = objects.getIterator();
		NonPlayerRobot npr = null;
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof NonPlayerRobot) {
				npr = (NonPlayerRobot) obj;
				if (npr.getStrategy() == "ChaseRobot") {
					npr.setStrategy(new NextBase());
					npr.invokeStrategy(this, npr, 10);
				} else {
					npr.setStrategy(new ChaseRobot());
					npr.invokeStrategy(this, npr, 10);
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	public PlayerRobot getRobot() {
		IIterator iter = objects.getIterator();
		PlayerRobot pr = null;
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof PlayerRobot) {
				pr = ((PlayerRobot) obj);
			}
		}
		return pr;
	}

	public void pauseGame(UITimer timer, Game game) {
		if (isPaused() == false) {
			setPause(true);
			timer.cancel();
		} else {
			setPause(false);
			timer.schedule(20, true, game);
		}
		bSound.play();
		setChanged();
		notifyObservers(this);
	}

	// TODO adjust acceleration according to energy level and damage
	public void accelerate() {
		System.out.println("Accelerating");
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof PlayerRobot) {
				PlayerRobot robot = (PlayerRobot) obj;
				if (robot.getSpeed() + 3 > robot.getMaximumSpeed()) {
					robot.setSpeed(robot.getMaximumSpeed());
				} else { // otherwise increment speed by 5
					robot.setSpeed(robot.getSpeed() + 3);
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	public void brake() {
		System.out.println("Breaking");
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof PlayerRobot) {
				PlayerRobot robot = (PlayerRobot) obj;
				if (robot.getSpeed() - 5 < 0) {
					robot.setSpeed(0);
				} else {
					robot.setSpeed(robot.getSpeed() - 5);
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	public void steerLeft() {
		System.out.println("Steering Left");

		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof PlayerRobot) {
				PlayerRobot robot = (PlayerRobot) obj;
				if (robot.getSpeed() > 0) {
					if (robot.getSteeringDirection() - 1 < -40) { // check if steering left will lead to less than -40
						robot.turn(-40);
					} else { // otherwise decrease steering direction by 5
						robot.turn(robot.getSteeringDirection() - 1);
					}
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	public void steerRight() {
		System.out.println("Steering Right");
		// changing steering direction of the robot 5 degree to the right. It changes
		// the direction, not the heading
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof PlayerRobot) {
				PlayerRobot robot = (PlayerRobot) obj;
				if (robot.getSpeed() > 0) {
					if (robot.getSteeringDirection() + 1 > 40) { // check if steering right will lead to more than 40
						// degrees
						robot.turn(40);
					} else { // otherwise decrease steering direction by 5
						robot.turn(robot.getSteeringDirection() + 1);
					}
				}
			}
		}
	}

	public void robotCollision(PlayerRobot pr, NonPlayerRobot npr) {
		prWithNprSound.play(this);
		if (pr.getDamageLevel() + 5 > pr.getMaxDamageLevel()) {
			pr.setDamageLevel(pr.getMaxDamageLevel());
		} else {
			pr.setColor(pr.getRed(), pr.getGreen() + 20, pr.getBlue() + 20);
			pr.setDamageLevel(pr.getDamageLevel() + 5);
			if (pr.getMaximumSpeed() > 2) {
				pr.setSpeed(pr.getSpeed() - 1);
				pr.setMaximumSpeed(pr.getMaximumSpeed() - 2);
			}
		}

		if (npr.getDamageLevel() + 5 > npr.getMaxDamageLevel()) {
			npr.setDamageLevel(npr.getMaxDamageLevel());
		} else {
			npr.setDamageLevel(npr.getDamageLevel() + 5);
			npr.setMaximumSpeed(npr.getMaximumSpeed() - 5);
			if (npr.getSpeed() > 1) {
				npr.setSpeed(npr.getSpeed() - 1);
			}
		}

		setChanged();
		notifyObservers(this);
	}

	public void droneColission(Robot robot) {
		prWithDroneSound.play(this);
		if (robot.getDamageLevel() + 2 > robot.getMaxDamageLevel()) {
			robot.setDamageLevel(robot.getMaxDamageLevel());
		} else {
			robot.setColor(robot.getRed(), robot.getGreen() + 20, robot.getBlue() + 20);
			robot.setSpeed(robot.getSpeed() - 1);
			robot.setDamageLevel(robot.getDamageLevel() + 2);
			if (robot.getMaximumSpeed() > 2) {
				robot.setMaximumSpeed(robot.getMaximumSpeed() - 2);
			}
		}

		setChanged();
		notifyObservers(this);
	}

	public void esCollision(EnergyStation es, Robot robot) {
		if (es.getCapacity() > 0) {
			prWithEsSound.play(this);
			if (robot.getEnergyLevel() + es.getCapacity() > 100) {
				robot.setEnergyLevel(robot.getMaxEnergyLevel());
				if (robot instanceof PlayerRobot) {
					robot.setColor(255, 0, 0);
				}
			} else {
				robot.setEnergyLevel(robot.getEnergyLevel() + es.getCapacity()); // recharghe energy level
				if (robot instanceof PlayerRobot) {
					robot.setColor(robot.getRed(), robot.getGreen() - 30, robot.getBlue() - 20);
				}
			}
			robot.setMaximumSpeed(10);
			if (robot.getSpeed() < 3) {
				robot.setSpeed(3);
			}
			es.drain(); // set energy station capacity to 0;
			objects.add(new EnergyStation(rand.nextInt(200 - 50) + 50, rand.nextInt(this.width - 200),
					rand.nextInt(this.height - 200), width, height));
			robot.setMaximumSpeed(robot.getMaximumSpeed()); // set maximum speed back to normal
		}

		setChanged();
		notifyObservers(this);
	}

	public void baseCollide(Robot robot, Base base) {
		prWithBaseSound.play(this);
		if (base.getSequenceNumber() == robot.getLastBaseReached() + 1) {
			robot.setBase(robot.getLastBaseReached() + 1);
			if (base.getSequenceNumber() == 4) {
				if (robot instanceof PlayerRobot) {
					System.out.println("Game Over! You won");
					pauseGame(this.timer, this.game);

				} else if (robot instanceof NonPlayerRobot) {
					System.out.println("Game Over! NPR won");
					pauseGame(this.timer, this.game);
				}
			}

		}
		setChanged();
		notifyObservers(this);
	}

	public void tickClock(UITimer timer) {
		this.clockTime++;
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof MovableObject && !(obj instanceof NonPlayerRobot)) {
				MovableObject movObj = (MovableObject) obj;
				movObj.move(width, height, 10);
			}
			if (obj instanceof NonPlayerRobot) {
				NonPlayerRobot npr = (NonPlayerRobot) obj;
				npr.move(this, npr, width, height, 20);
			}
			if (obj instanceof PlayerRobot) {
				PlayerRobot pr = (PlayerRobot) obj;
				if (pr.getEnergyLevel() <= 0) {
					System.out.println("Game over, Player Robot ran out of energy!");
					robotExp.play(this);
					this.remainingLives--;
					pr.respawn();
				} else if (pr.getDamageLevel() >= pr.getMaxDamageLevel()) {
					robotExp.play(this);
					System.out.println("Player Robot took Max Damage Level");
					this.remainingLives--;
					pr.respawn();
				} else if (this.remainingLives <= 0) {
					robotExp.play(this);
					System.out.println("Game over, Player Robot ran out of lives!");
					timer.cancel();
					// exitGame();
				}
			}

		}

		iter = objects.getIterator();
		while (iter.hasNext()) {
			ICollider objA = (ICollider) iter.getNext();
			IIterator iter2 = objects.getIterator();
			while (iter2.hasNext()) {
				ICollider objB = (ICollider) iter2.getNext();
				if (objB != objA && objA.collidesWith(objB)) {
					objA.handleCollision(objB, this);
				}
			}
		}
		setChanged();

		notifyObservers(this);
	}

	public void init(UITimer timer, Game game) {
		this.timer = timer;
		this.game = game;
		PlayerRobot playerRobot = PlayerRobot.getPlayerRobot(80, 510, 80);
		objects.add(new Base(100, 250, 300, 1, width, height));
		objects.add(new Base(100, 300, 1000, 2, width, height));
		objects.add(new Base(100, 1100, 100, 3, width, height));
		objects.add(new Base(100, 1100, 800, 4, width, height));
		objects.add(new Drone(80, rand.nextInt(this.width - 200) + 200, rand.nextInt(this.height - 200) + 200,
				rand.nextInt(359), 5));
		objects.add(
				new Drone(80, rand.nextInt(this.width - 200) + 200, rand.nextInt(height - 200), rand.nextInt(359), 5));
		objects.add(new EnergyStation(rand.nextInt(150 - 50) + 50, rand.nextInt(this.width - 200) + 200,
				rand.nextInt(this.height - 200), width, height));
		objects.add(playerRobot);
		objects.add(new NonPlayerRobot(80, 80, 80, new NextBase()));
	}
}
