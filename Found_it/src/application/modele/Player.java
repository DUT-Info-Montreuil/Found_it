package application.modele;


public class Player extends MainCharacter {
		
	public Player(int x,int y, TileMap map, int speed) {
		super(x,y,map);
		setVIT(speed);
	}
	
	
}
