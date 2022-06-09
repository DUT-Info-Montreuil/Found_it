package application.modele;

import application.modele.algo.BFS;

public class Slime extends Enemy{

    private final int att = 5;
    private final static int speed = 1, range = 10, jumpMax = 4;

    public Slime(int x, int y, TileMap map, int att, int pv,Environment env) {
        super(x, y, map, att, pv, range,jumpMax,env);
        setVIT(speed);
    }

    public void update(Player player, BFS bfs) {
        if (bfs.isNear(this, getRangeDiffBlocMax()))
                goAttacking();
            if (isAttacking())
                attack(player, bfs);
            else 
                harmless();
            jump();
            if (getLeftPressed())
                moveLeft();
            if (getRightPressed())
                moveRight();
            gravity();
    }





}
