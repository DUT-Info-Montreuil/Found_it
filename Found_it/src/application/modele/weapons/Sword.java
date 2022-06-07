package application.modele.weapons;

import application.modele.Enemy;
import application.modele.Environment;
import application.modele.MainCharacter;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.Tool;

public class Sword extends Tool{
    
    private Player player;
    private TileMap mapTile;
    private Environment environment;
    private int range;
    private int damage;

    public Sword(TileMap map, int damage, int range, Player p) {
        player = p;
        mapTile = map;
        environment = player.getEnvironment();
        this.range = range;
        this.damage = damage;
    }

    public void use(int x, int y) {
        if (!player.isInInventory()) 
            for (Enemy e : environment.isNearPlayer(range))
                e.takeDamage(damage);
    }

}