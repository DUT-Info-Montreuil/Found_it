package application.modele;

public class Pelle extends Tool{
	
	private TileMap mapTile;
    private int range;
    private Player player;

	public Pelle(TileMap map, int range, Player p) {
		mapTile = map;
		this.range = range;
		player = p;
	}

	public void use(int x, int y) {
		if (!player.isInInventory()) 
			if (canActiveWithRangeX(x) && canActiveWithRangeY(y))
				mapTile.remove(x, y);
	}

	public boolean canActiveWithRangeX(int x) {
		return Math.abs(player.getX() - x) <= range || Math.abs(player.getXRight() - x) <= range;
	}
	public boolean canActiveWithRangeY(int y) {
		return Math.abs(player.getY() - y) <= range || Math.abs(player.getYBOT() - y) <= range;
	}
	
}
