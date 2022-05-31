package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class MainCharacter {

	private IntegerProperty x, y;
	private final static int PIXELCHARACTER = 32 - 4;
	private int VIT, att;
	protected TileMap mapTile;
	private int jumpBlock = 0, jumpMAX;
	private boolean isFalling = false, isJumping = false;
	private IntegerProperty hp;
	private IntegerProperty dx;
	private int rangeAttack = 5;
	
	public MainCharacter(int x,int y, TileMap map, int att, int pv, int jumpMax) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		mapTile = map;
		this.jumpMAX = jumpMax;
		this.att = att;
		this.hp = new SimpleIntegerProperty(pv);
		dx = new SimpleIntegerProperty(1);
	}
	public IntegerProperty getXProperty() {
		return x;
	}
	public IntegerProperty getYProperty() {
		return y;
	}

	public int getRangeForAttack() {
		return rangeAttack;
	}

	public void setX(int value) {
		x.set(value);
	}
	public void setY(int value) {
		y.set(value);
	}
	public int getX() {
		return x.getValue();
	}
	public int getXRight() {
		return x.getValue() + PIXELCHARACTER ;
	}
	public int getYBOT() {
		return y.getValue() + PIXELCHARACTER  ;
	}
	public int getY() {
		return y.getValue() ;
	}
	
	public void setIsJumping(boolean b) {
		if (!isFalling)
			isJumping = b;
	}

	public void setVIT(int value) {
		VIT = value;
	}
	public int getVIT() {
		return VIT;
	}

	public IntegerProperty getHpProperty() {
		return hp;
	}

	public int getHp() {
		return hp.getValue();
	}

	public void setDirectionnalX(int value) {
		dx.set(value);
	}

	public int getDirectionnalX() {
		return dx.getValue();
	}

	public IntegerProperty getDirectionnalXProperty() {
		return dx;
	}

	public int getJumpMax() {
		return jumpMAX;
	}
	
	public void moveRight() {
		if (mapTile.wasTransparent(mapTile.getCodeTuile(getXRight() + VIT, getYBOT())))
			x.set(getX()+VIT);
		setDirectionnalX(1);
	}
	public void moveLeft() {
		if (mapTile.wasTransparent(mapTile.getCodeTuile(getX() - VIT, getYBOT())))
			x.set(getX()-VIT);
		setDirectionnalX(-1);
	}
	
	public void gravity() {
		if (!isJumpingBoolean()) {
			isFalling = true;
			launchGravity();
		}
	}
	public void launchGravity() {
		if(mapTile.wasTransparent(mapTile.getCodeTuile(getX(), getYBOT() + 3)) 
		&& mapTile.wasTransparent(mapTile.getCodeTuile(getXRight(), getYBOT() + 3)))
			y.set(y.getValue()+3);
		else
			isFalling = false;
	}
	
	
	public boolean isFallingBoolean() {
		return isFalling;
	}
	
	public void jump() {
		if (!isFallingBoolean()){
			isJumping = true;
 			launchJump();
		}
	}
	public void launchJump() {
		if (jumpBlock >= jumpMAX*mapTile.getPIXELBLOCK() - 1 
		|| !mapTile.wasTransparent(mapTile.getCodeTuile(getX(), getY() - 3)) 
		|| !mapTile.wasTransparent(mapTile.getCodeTuile(getXRight(), getY() - 3))) {
			jumpBlock = 0;
			isJumping = false;
			gravity();
		}
		else  {
			y.set(getY()-3);
			jumpBlock += 3;
		}
	}
	
	public boolean isJumpingBoolean() {
		return isJumping;
	}
	public int getPixelCharacter() {
		return PIXELCHARACTER;
	}
	public boolean isAlive() {
		return hp.getValue()>0;
	}
    public void sendDamage(MainCharacter m) {
    	m.takeDamage(att);
    }
    public void takeDamage(int damage) {
    	hp.set(hp.getValue() - damage);
    }

	public int getAtt() {
		return att;
	}


	public boolean isNearX(MainCharacter m) {
		return Math.abs(getX() - m.getXRight()) < rangeAttack 
			|| Math.abs(getXRight() - m.getX()) < rangeAttack
			|| (getXRight() >= m.getXRight() && getX() <= m.getXRight()) 
			|| (getXRight() >= m.getX() && getX() <= m.getX());
	}
	public boolean isNearY(MainCharacter m) {
		return (getYBOT() >= m.getYBOT() && getY() <= m.getYBOT()) || (getYBOT() >= m.getY() && getY() <= m.getY());
	}

	public boolean isNear(MainCharacter m) {
		return isNearX(m) && isNearY(m);
	}
}
