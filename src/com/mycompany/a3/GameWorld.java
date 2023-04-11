package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.mycompany.fixedObjects.Base;
import com.mycompany.fixedObjects.EnergyStation;
import com.mycompany.interfaces.IIterator;
import com.mycompany.movableObjects.Drone;
import com.mycompany.movableObjects.NonPlayerRobot;
import com.mycompany.movableObjects.Robot;
import com.codename1.ui.Command;

public class GameWorld extends Observable {
	private GameCollection objects;
	private int clockTime;
	private int remainingLives;
	private boolean sound;
	public Random rand = new Random();

	public GameWorld() {
		this.objects = new GameCollection();
		this.clockTime = 0;
		this.remainingLives = 3;
		this.sound = false;
	}

	public void changeStrategy() {
		IIterator iter = objects.getIterator();
		NonPlayerRobot npr = null;
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof NonPlayerRobot) {
				npr = (NonPlayerRobot) obj;
				npr.setStrategy();
			}
		}
		System.out.println(npr);
	}

	public Robot getRobot() {
		IIterator iter = objects.getIterator();
		Robot robot = null;
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				robot = ((Robot) obj);
			}
		}
		return robot;
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
		this.sound = !sound;
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

	public void showMap() {
		tickClock();
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			System.out.println(obj.getClass().getSimpleName() + ": " + obj);
		}
	}

	public void exitGame() {
		System.exit(0);
	}

	// TODO adjust acceleration according to energy level and damage
	public void accelerate() {
		System.out.println("Accelerating");
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
				if (robot.getSpeed() + 5 > robot.getMaximumSpeed()) {
					robot.setSpeed(robot.getMaximumSpeed());
				} else { // otherwise increment speed by 5
					robot.setSpeed(robot.getSpeed() + 5);
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
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
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
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
				if (robot.getSteeringDirection() - 5 < -40) { // check if steering left will lead to less than -40
					robot.turn(-40);
				} else { // otherwise decrease steering direction by 5
					robot.turn(robot.getSteeringDirection() - 5);
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
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
				if (robot.getSteeringDirection() + 5 > 40) { // check if steering right will lead to more than 40
					// degrees
					robot.turn(40);
				} else { // otherwise decrease steering direction by 5
					robot.turn(robot.getSteeringDirection() + 5);
				}
			}
		}
	}

	public void robotCollision() {
		// pretend it collided with another robot. Increases damage level and fades the
		// color. If damage level causes
		// robot to not be able to move: end game, player loses a life
		System.out.println("Robot Collision");
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
				if (robot.getDamageLevel() + 5 > robot.getMaxDamageLevel()) {
					this.remainingLives -= 1;
					System.out.println("The player took max damage level.Game Over");
					if (this.remainingLives < 0) {
						System.out.println("Game Over, you failed!");
						exitGame();
					} else {
						objects.remove(robot); // remove the robot from the array
						objects.add(new Robot(150, 250, 300)); // add robot with fresh proporties
					}

				} else {
					robot.setDamageLevel(robot.getDamageLevel() + 5);
					robot.setColor(robot.getColor() + 30); // Make Color Brighter
					robot.setMaximumSpeed(robot.getMaximumSpeed() - 5);
				}
			}

			if (obj instanceof NonPlayerRobot) {
				NonPlayerRobot npr = (NonPlayerRobot) obj;
				if (npr.getDamageLevel() + 5 > npr.getMaxDamageLevel()) {
					npr.setDamageLevel(0);
				} else {
					npr.setDamageLevel(npr.getDamageLevel() + 5);
				}

			}
		}
		nprInfo();
		setChanged();
		notifyObservers(this);
	}

	public void nprInfo() {
		IIterator iter = objects.getIterator();
		NonPlayerRobot npr = null;
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof NonPlayerRobot) {
				npr = (NonPlayerRobot) obj;
				System.out.println(npr);
			}
		}
	}

	public void droneColission() {
		System.out.println("Collided with Drone");
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
				if (robot.getDamageLevel() + 2 > robot.getMaxDamageLevel()) {
					this.remainingLives -= 1;
					System.out.println("The player took max damage level.Game Over");
					if (this.remainingLives < 0) {
						System.out.println("Game Over, you failed!");
						exitGame();
					} else {
						objects.remove(robot); // remove the robot from the array
						objects.add(new Robot(150, 250, 300)); // add robot with fresh proporties
					}

				} else {
					robot.setDamageLevel(robot.getDamageLevel() + 2);
					robot.setColor(robot.getColor() + 30); // Make Color Brighter
					robot.setMaximumSpeed(robot.getMaximumSpeed() - 5);
					System.out.println(robot);
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	public void esCollision() {

		System.out.println("Collided with Energy Station");
		IIterator iter = objects.getIterator();
		Robot robot = null;
		EnergyStation es = null;
		ArrayList<EnergyStation> esStations = new ArrayList<EnergyStation>();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				robot = (Robot) obj;
			} else if (obj instanceof EnergyStation) {
				esStations.add((EnergyStation) obj);
			}
		}
		// Chooose random es for the robot to collide with
		es = esStations.get(rand.nextInt(esStations.size()));
		System.out.println("robot: " + robot.getEnergyLevel() + " ES: " + es.getCapacity());
		if (robot.getEnergyLevel() + es.getCapacity() > 100) {
			robot.setEnergyLevel(100);
		} else {
			robot.setEnergyLevel(robot.getEnergyLevel() + es.getCapacity()); // recharghe energy level
		}
		es.drain(); // set energy station capacity to 0;
		es.setColor(es.getColor() + 30); // faint color
		objects.add(new EnergyStation(rand.nextInt(50), rand.nextDouble(), rand.nextDouble()));
		robot.setMaximumSpeed(50); // set maximum speed back to normal

		setChanged();
		notifyObservers(this);
	}

	public void tickClock() {
		this.clockTime++;
		System.out.println("Tick");
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof MovableObject) {
				MovableObject movObj = (MovableObject) obj;
				movObj.move();
				// check if robot ran out of energy
			} else if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
				if (robot.getEnergyLevel() <= 0) {
					System.out.println("Game over, you ran out of energy!");
					if (this.remainingLives - 1 < 0) {
						System.out.println("Game over, you ran out of lives!");
						exitGame();
					}
					this.remainingLives--;
					objects.remove(robot);
					objects.add(new Robot(40, 250, 300 + 80));
				}

			}
		}
		setChanged();
		notifyObservers(this);
	}

	// TODO get base number though dialog
	public void baseCollide() {
		int baseNumber = 0;
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[] { cOk, cCancel };
		TextField myTF = new TextField();
		Command c = Dialog.show("Enter Base Number (1-4)", myTF, cmds);
		if (c == cOk) { // check if ok or cancel
			baseNumber = Integer.parseInt(myTF.getText());
		}
		IIterator iter = objects.getIterator();
		while (iter.hasNext()) {
			GameObject obj = iter.getNext();
			if (obj instanceof Robot && !(obj instanceof NonPlayerRobot)) {
				Robot robot = (Robot) obj;
				if (robot.getLastBaseReached() + 1 == baseNumber) { // check if base# is one more than the last base
					// reached
					robot.setBase(baseNumber);
					System.out.println("Last base reached: " + robot.getLastBaseReached());
				}
				if (robot.getLastBaseReached() == 4) {
					System.out.println("Game Over, you win! Total time: " + this.clockTime);
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	public void init() {
		objects.add(new Base(100, 250, 300, 1));
		objects.add(new Base(100, 500, 500, 2));
		objects.add(new Base(100, 800, 100, 3));
		objects.add(new Base(100, 100, 800, 4));
		objects.add(new Drone(80, rand.nextInt(1024), rand.nextInt(768), rand.nextInt(359), 5 + rand.nextInt(10)));
		objects.add(new Drone(80, rand.nextInt(1024), rand.nextInt(768), rand.nextInt(359), 5 + rand.nextInt(10)));
		objects.add(new EnergyStation(rand.nextInt(150), rand.nextInt(1024), rand.nextInt(768)));
		objects.add(new EnergyStation(rand.nextInt(150), rand.nextInt(1024), rand.nextInt(768)));
		objects.add(new Robot(80, 250, 300 + 80));
		objects.add(new NonPlayerRobot(90, 250 + 80, 300 + 160, 1));
		objects.add(new NonPlayerRobot(90, 250 - 40, 300 + 80, 1));
		objects.add(new NonPlayerRobot(90, 250 + 40, 300 - 80, 2));
	}
}
