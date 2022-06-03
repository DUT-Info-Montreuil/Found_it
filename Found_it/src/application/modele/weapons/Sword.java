package application.modele.weapons;

import application.modele.Enemy;
import application.modele.Player;
import application.modele.TileMap;
import application.modele.Environment;

public class Sword extends Weapon{
    
    public Sword(TileMap map, Environment env, int range, Player p) {
        super(map, env, range, p);
    }

    public void attack(int x, int y, Enemy e,Environment env){
        if (canAttackRangeX(x) && canAttackRangeY(y)){
            env.removeEnemy(e);
        }

    }


}
