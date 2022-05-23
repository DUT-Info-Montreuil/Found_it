package application.modele;

import application.modele.algo.BFS;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public abstract class Enemy extends MainCharacter{
    
    private int tps = 0;
    private final static int tpsHarmlessRotate = 100;
    private int rangeForAttack;
    private boolean isAttacking;
    private boolean left = false, right = false;
    private BFS bfs;

    public Enemy (int x, int y, TileMap map, int att, int pv, int range, int jumpMax) {
        super(x, y, map, att, pv, jumpMax);
        rangeForAttack = range;
        isAttacking = false;
    }

    public int getRangeForAttack() {
        return rangeForAttack;
    }

    public void goAttacking() {
        isAttacking = true;
    }
    public void setLeft(boolean b) {
        left = b;
    }
    public void setRight(boolean b) {
        right = b;
    }
    public boolean rightPressedBoolean() {
        return right;
    }
    public boolean leftPressedBoolean() {
        return left;
    }

    public void attack(BFS bfs) {
        this.bfs = bfs;
        if (!isAlive() || !bfs.isNear(this, this.getRangeForAttack()))
            isAttacking = false;
        else {
            if (bfs.goRight(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getY()))) 
                setRight(true);
            else if (bfs.goLeft(mapTile.getXCharacterInMap(getXRight()), mapTile.getYIndiceHeight(getY()))) 
                setLeft(true);
            else if (bfs.goUp(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getY())))
                setIsJumping(true);
            }
            if (!bfs.goRight(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getY())))
                setRight(false);
            if (!bfs.goLeft(mapTile.getXCharacterInMap(getXRight()), mapTile.getYIndiceHeight(getY())))
                setLeft(false);
	}

    public boolean isAttacking() {
        return isAttacking;
    }


    public void harmless() {
        if (isAttacking == true)
            tps = 0;
        else {
            if (tps == tpsHarmlessRotate * 5)
                tps = 0;
            if (tps == 0) 
                if ((int)(Math.random()*2) == 0)
                    setRight(true);
                else
                    setLeft(true);
            if (tps > tpsHarmlessRotate) {
                setRight(false);
                setLeft(false);
            }
        }
        tps++;
        gravity();
	}



}
