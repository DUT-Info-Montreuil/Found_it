package application.modele;

public abstract class Tool {
    
    private TileMap mapTile;
    private int range;
    private Player player;

    public Tool(TileMap map, int range, Player p) {
		mapTile = map;
		this.range = range * mapTile.getPIXELBLOCK();
        player = p;
	}

    public int getRange() {
        return range;
    }
    public TileMap getMap() {
        return mapTile;
    }
    public boolean canActiveWithRangeX(int x) {
		return Math.abs(player.getX() - x) <= getRange() || Math.abs(player.getXRight() - x) <= getRange();
	}
	public boolean canActiveWithRangeY(int y) {
		return Math.abs(player.getY() - y) <= getRange() || Math.abs(player.getYBOT() - y) <= getRange();
	}

    public abstract void use(int x, int y);

}
