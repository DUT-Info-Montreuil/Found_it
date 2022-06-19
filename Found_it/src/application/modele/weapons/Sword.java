package application.modele.weapons;

import application.modele.Enemy;
import application.modele.Environment;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.Tool;

public class Sword extends Tool{
    
    private Environment environment;
    private int damage;
    private int rangeAttack;

    public Sword(TileMap map, int damage, int range, Player p) {
        super(map,0,p);
        environment = getPlayer().getEnvironment();
        this.damage = damage;
        rangeAttack = range;
    }

    public void use(int x, int y) {
        if (!getPlayer().isInInventory()) 
            for (Enemy e : environment.isNearPlayer(rangeAttack))
                e.takeDamage(damage);
    }

}