package application.modele;

import application.modele.algo.BFS;


public abstract class Enemy extends MainCharacter{
    
    private int tps = 0;
    private final static int tpsHarmlessRotate = 100, delayAttack = 150;
    private boolean isAttacking;
    private boolean left = false, right = false;
    private int rangeDiffBlockMax, rangeForAttack;

    public Enemy (int x, int y, TileMap map, int att, int pv, int range, int jumpMax,Environment env) {
        super(x, y, map, att, pv, jumpMax,env);
        rangeDiffBlockMax = range;
        isAttacking = false;
    }


    public void setRangeForAttack(int value) {
        rangeForAttack = value;
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
    public int getRangeDiffBlocMax() {
        return rangeDiffBlockMax;
    }

    public void attack(Player p,BFS bfs) {
        if (!isAlive() || !bfs.isNear(this, getRangeDiffBlocMax()))
            isAttacking = false;
            if (!isAlive())
                getEnvironment().removeEnnemie(this);
        else {
            if (bfs.goRight(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getYBOT()))) 
                setRight(true);
            else if (bfs.goLeft(mapTile.getXCharacterInMap(getXRight()), mapTile.getYIndiceHeight(getYBOT()))) 
                setLeft(true);
            else if (bfs.goUp(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getYBOT())))
                setIsJumping(true);
            }
            if (!bfs.goRight(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getYBOT())))
                setRight(false);
            if (!bfs.goLeft(mapTile.getXCharacterInMap(getXRight()), mapTile.getYIndiceHeight(getYBOT())))
                setLeft(false);
            attacking(p);
            tps++;
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

    public void attacking(MainCharacter p) {
        if (isNear(p) && tps >= delayAttack) {
            giveDamage(p);
            tps = 0;
        }
    }

    public void giveDamage(MainCharacter m) {
        m.takeDamage(getAtt());
    }

    public void takeDamage(Enemy e){
        e.takeDamage(getAtt());
    }


    public void update(Player player, BFS bfs) {
        if (bfs.isNear(this, getRangeDiffBlocMax()))
                goAttacking();
            if (isAttacking())
                attack(player, bfs);
            else 
                harmless();
            if (isJumpingBoolean())
                jump();
            if (leftPressedBoolean())
                moveLeft();
            if (rightPressedBoolean())
                moveRight();
            gravity();
    }

    public int getTps() {
        return tps;
    }
    public void setTps(int value){
        tps = value;
    }
    public int getDelayAttack() {
        return delayAttack;
    }

    public boolean isNearX(MainCharacter m) {
		return Math.abs(getX() - m.getXRight()) < rangeForAttack 
			|| Math.abs(getXRight() - m.getX()) < rangeForAttack
			|| (getXRight() >= m.getXRight() && getX() <= m.getXRight()) 
			|| (getXRight() >= m.getX() && getX() <= m.getX());
	}
	public boolean isNearY(MainCharacter m) {
		return (getYBOT() >= m.getYBOT() && getY() <= m.getYBOT()) || (getYBOT() >= m.getY() && getY() <= m.getY());
	}

	public boolean isNear(MainCharacter m) {
		return isNearX(m) && isNearY(m);
	}



}
