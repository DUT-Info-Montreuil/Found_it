package application.modele;

public class ConstructionPlan extends Tool{

    private int blockInHand;

    public ConstructionPlan(TileMap map, int range, Player p, int idblock) {
        super(map,range,p);
        blockInHand = idblock;
    }

    public void use(int x, int y) {
        if (!getPlayer().getInventory().alreadyInInventory(blockInHand))
            getPlayer().setTools(null);
        else if (canActiveWithRangeX(x) && canActiveWithRangeY(y) && getMap().getIndice(x, y) != getMap().getIndice(getPlayer().getX(), getPlayer().getY())){
			if(getMap().wasTransparent(getMap().getCodeTuile(x, y))) {
				getPlayer().getInventory().getResourcesId(blockInHand).removeQuantity();
				getMap().replace(x, y, blockInHand);
			}		
		}
    }

}