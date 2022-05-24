package application.modele;

public class Pelle extends Tool{
	
	
	public Pelle(TileMap map, int range, Player p) {
		super(map,range,p);
	}

	public void use(int x, int y) {
		if (canActiveWithRangeX(x) && canActiveWithRangeY(y))
			getMap().remove(x, y);
	}
	
}
