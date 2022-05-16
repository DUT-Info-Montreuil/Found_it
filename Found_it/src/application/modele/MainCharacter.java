package application.modele;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class MainCharacter {

	private IntegerProperty x;
	private IntegerProperty y;
	private final int PIXELCHARACTER = 32;
	private int VIT;
	private TileMap mapTile;
	private final int[] LISTIDBLOCKTRANSPARENT = {112};  //TODO dans TileMap
	Timeline gravity;
	Timeline jump;
	private int jumpBlock;
	private final int jumpMAX;
	private double rapidityOfGravity = 0.007;
	private double rapidityOfJump = 0.007;
	
	public MainCharacter(int x,int y, TileMap map) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		mapTile = map;
		jumpMAX = 3 * mapTile.getPIXELBLOCK();
		initGravity();
		initJump();
	}
	public IntegerProperty getXProperty() {
		return x;
	}
	public IntegerProperty getYProperty() {
		return y;
	}
	public int getX() {
		return x.getValue();
	}
	public int getYBOT() {
		return y.getValue() + PIXELCHARACTER;
	}
	public int getY() {
		return y.getValue();
	}
	
	public void setVIT(int value) {
		VIT = value;
	}
	public int getVIT() {
		return VIT;
	}
	
	public void deplacementDroite() {
		if (wasTransparent(mapTile.getIndice(getX()+ PIXELCHARACTER + VIT, getYBOT())))
			x.set(getX()+VIT);	
	}
	public void deplacementGauche() {
		if (wasTransparent(mapTile.getIndice(getX() - VIT, getYBOT())))
			x.set(getX()-VIT);
	}
	
	private boolean wasTransparent(int block) {
		int i = 0;
		while (i < LISTIDBLOCKTRANSPARENT.length && LISTIDBLOCKTRANSPARENT[i] != block)
			i++;
		return i < LISTIDBLOCKTRANSPARENT.length;
	}
	
	public void gravity() {
		if (jump.getStatus() == Status.STOPPED)
			gravity.play();
	}
	
	public void initGravity() {
		gravity = new Timeline();
		gravity.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(rapidityOfGravity),(ev ->{
			if(wasTransparent(mapTile.getIndice(getX(), getYBOT() + 1)) && wasTransparent(mapTile.getIndice(getX() + PIXELCHARACTER, getYBOT() + 1)))
				y.set(y.getValue()+1);
			else
				gravity.stop();
		}));
		gravity.getKeyFrames().add(kf);
	}
	
	public void jump() {
		if (gravity.getStatus() == Status.STOPPED)
			jump.play();
	}
	public void initJump() {
		jump = new Timeline();
		jumpBlock = 0;
		jump.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(rapidityOfJump),(ev ->{
			if (jumpBlock == jumpMAX) {
				jumpBlock = 0;
				jump.stop();
				gravity.play();
			}
			else 
				y.set(getY()-1);
			jumpBlock++;
		}));
		jump.getKeyFrames().add(kf);
	}
	
}
