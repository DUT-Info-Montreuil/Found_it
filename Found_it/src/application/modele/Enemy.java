package application.modele;

import application.modele.algo.BFS;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public abstract class Enemy extends MainCharacter{
    
    private Timeline attack;
    private Timeline harmless;
    private int tps;
    private final static int tpsHarmlessRotate = 100;
    private int rangeForAttack;
    private boolean isAttacking;
    private BFS bfs;

    public Enemy (int x, int y, TileMap map, int att, int pv, int range, int jumpMax) {
        super(x, y, map, att, pv, jumpMax);
        rangeForAttack = range;
        isAttacking = false;
        initHarmless();
    }

    public int getRangeForAttack() {
        return rangeForAttack;
    }

    public void attack() {
        isAttacking = true;
        attack.play();
    }

    public void initAttack(MainCharacter carac, BFS bfs) {
        this.bfs = bfs;
		attack = new Timeline();
		attack.setCycleCount(attack.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.014),(ev ->{
            if (!isAlive() || !bfs.isNear(this, this.getRangeForAttack())) {
                isAttacking = false;
                attack.stop();
            } 
            else {
                if (bfs.goRight(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getY())))
                    moveRight();
                else if (bfs.goLeft(mapTile.getXCharacterInMap(getXRight()), mapTile.getYIndiceHeight(getY()))) 
                    moveLeft();
                else if (bfs.goUp(mapTile.getXCharacterInMap(getX()), mapTile.getYIndiceHeight(getY())))
                    jump();
            }
            gravity();   
		}));
		attack.getKeyFrames().add(kf);
	}

    public boolean isAttacking() {
        return isAttacking;
    }

    public void harmless() {
        harmless.play();
    }

    public void initHarmless() {
		harmless = new Timeline();
        tps = 0;
		harmless.setCycleCount(harmless.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.014),(ev ->{
            if (isAttacking == true) {
                tps = 0;
                harmless.stop();
            }
            else {
                if (tps == tpsHarmlessRotate * 5)
                    tps = 0;
                if (tps == 0) 
                    if ((int)(Math.random()*2) == 0)
                        setDirectionnalX(1);
                    else
                        setDirectionnalX(-1);
                if (tps <= tpsHarmlessRotate) {
                    if (getDirectionnalX() == 1)
                        moveRight();
                    else
                        moveLeft();
                }
            } 
            tps++;
            gravity();   
		}));
		harmless.getKeyFrames().add(kf);
	}



}
