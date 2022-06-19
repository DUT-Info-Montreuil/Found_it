package application.modele.weapons;

import application.modele.Enemy;
import application.modele.Environment;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.Tool;

public class Sword extends Tool{
    
    private Environment environment;
    private int damage;

    public Sword(TileMap map, int damage, int range, Player p) {
        super(map,range,p);
        environment = getPlayer().getEnvironment();
        this.damage = damage;
    }

    public void use(int x, int y) {
        if (!getPlayer().isInInventory()) 
            for (Enemy e : environment.isNearPlayer(getRange()))
                e.takeDamage(damage);
    }

}