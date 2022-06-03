package application.modele.weapons;

import application.modele.Enemy;
import application.modele.Environment;
import application.modele.Player;
import application.modele.TileMap;

public abstract class Weapon {

    private Environment environment;
    private TileMap mapTile;
    private int range;
    private Player player;

    public Weapon(TileMap map,Environment env , int range, Player p){
        this.mapTile = map;
        this.environment = env;
        this.range = range * mapTile.getPIXELBLOCK();
        this.player = p;
    }

    public int getRange(){
        return this.range;
    }

    public TileMap getMap(){
        return this.mapTile;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Environment getEnvironment(){
        return this.environment;
    }

    public boolean canAttackRangeX(int x){
       return canAttackRangeX(x);
    }

    public boolean canAttackRangeY(int y){
        return canAttackRangeY(y);
    }

    public abstract void attack(int x, int y, Enemy e,Environment env);




    
}
