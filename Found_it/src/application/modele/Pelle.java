package application.modele;

public class Pelle extends Tool{

	
	
	public Pelle(TileMap map, int range, Player p) {
		super(map,range,p);
	}

	public void use(int x, int y) {
		if (canActiveWithRangeX(x) && canActiveWithRangeY(y)){
			if(!getMap().wasIndestructible(getMap().getCodeTuile(x, y)) && !getMap().wasTransparent(getMap().getCodeTuile(x, y))) {
				getPlayer().getInventory().addInInventory(getMap().getCodeTuile(x, y));
				getMap().remove(x, y);
			}		
		}
	
	}







	


}
