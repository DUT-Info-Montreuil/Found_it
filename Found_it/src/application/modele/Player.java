package application.modele;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Player extends MainCharacter {
		
	private boolean leftPressed, rightPressed, upPressed;
	private BooleanProperty inInventory;
	private final static int jumpMax = 4;
	private Tool hand;
	
	public Player(int x,int y, TileMap map, int att, int pv, int speed) {
		super(x,y,map, att, pv, jumpMax);
		setVIT(speed);
		inInventory = new SimpleBooleanProperty(false);
		hand = new Pelle(map, 1, this);
	}
	
	public void stopAction() {
		setLeftPressed(false);
		setRightPressed(false);
		setUpPressed(false);
	}
	public void setLeftPressed(boolean b) {
		leftPressed = b;
	}
	public void setRightPressed(boolean b) {
		rightPressed = b;
	}
	public void setUpPressed(boolean b) {
		upPressed = b;
	}
	public void openCloseInventory() {
		inInventory.set(!inInventory.getValue());
	}
	public boolean getLeftPressedBoolean() {
		return leftPressed;
	}
	public boolean getRightPressedBoolean() {
		return rightPressed;
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
		if (!rightPressed && leftPressed)
			super.setDirectionnalX(-1);
		else if (rightPressed && !leftPressed)
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
	
	
}
