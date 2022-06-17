package application.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Player extends MainCharacter {
		
	private boolean upPressed;
	private BooleanProperty inInventory;
	private final static int jumpMax = 4;
	private Tool hand;
	
	public Player(int x,int y, TileMap map, int att, int pv, int speed,Environment env) {
		super(x,y,map, att, pv, jumpMax,env);
		setVIT(speed);
		inInventory = new SimpleBooleanProperty(false);
		hand = new Pelle(map, 1, this);
		env.setPlayer(this);
	}
	
	public void stopAction() {
		setLeftPressed(false);
		setRightPressed(false);
		setUpPressed(false);
	}
	public void setUpPressed(boolean b) {
		upPressed = b;
	}
	public void openCloseInventory() {
		inInventory.set(!inInventory.getValue());
	}
	public boolean getUpPressedBoolean() {
		return upPressed;
	}
	public boolean isInInventory() {
		return inInventory.getValue();
	}
	public BooleanProperty isInInventoryProperty() {
		return inInventory;
	}

	public void setDirectionnalX() {
		if (!getRightPressed() && getLeftPressed())
			super.setDirectionnalX(-1);
		else if (getRightPressed() && !getLeftPressed())
			super.setDirectionnalX(1);
	}

	public Tool getTools() {
		return hand;
	}

	public void setTools(Tool t) {
		hand = t;
	}

	public void useTool(int x, int y) {
		if (hand != null)
			hand.use(x,y);
	}

	public void update() {
		if (!isAlive())
			getEnvironment().removePlayer();
		if (!isInInventory()) {
			if (getLeftPressed())
				moveLeft();
			if (getRightPressed())
				moveRight();
			if (getUpPressedBoolean() || isJumpingBoolean())
				jump();
		}
		else
			if (isJumpingBoolean())
				jump();
		gravity();
		// if (environment.IsOnBFSzero(getX(),getY()))
		// 	environment.recalculateBFS();
	}
	
	
}
