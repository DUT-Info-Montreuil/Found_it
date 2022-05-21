package application.modele;

public class Pelle {
	
	private TileMap mapTile;
	private int range;
	private Player player;
	
	public Pelle(TileMap map, int range, Player p) {
		mapTile = map;
		this.range = range * mapTile.getPIXELBLOCK();
		player = p;
	}
	
	private int valAbs(int a) {
		if (a < 0)
			return -a;
		return a;
	}

	private boolean canDigX(int x) {
		return valAbs(player.getX() - x) <= range || valAbs(player.getXRight() - x) <= range;
	}
	private boolean canDigY(int y) {
		return valAbs(player.getY() - y) <= range || valAbs(player.getYBOT() - y) <= range;
	}
	
	public void dig(int x, int y) {
		if (canDigX(x) && canDigY(y))
			mapTile.remove(x, y);
	}
	
}
