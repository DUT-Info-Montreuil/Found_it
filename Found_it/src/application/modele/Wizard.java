package application.modele;

public class Wizard extends Enemy {
    
    private final int att = 10;
    private final static int speed = 1, range = 15, jumpMax = 6;
    private Magic magic;
    private static final int RANGEFORATTACKSQUELETON = 4 * 32;

    public Wizard(int x, int y, TileMap map, int att, int pv,Environment env) {
        super(x, y, map, att, pv, range, jumpMax,env);
        magic = new Magic(this, map, getEnvironment());
        setVIT(speed);
        setRangeForAttack(RANGEFORATTACKSQUELETON);
    }

    public void shoot(MainCharacter target) {
        magic.shoot(target);
    }

    public void attacking(MainCharacter p) {
        if (isNear(p) && getTps() >= getDelayAttack()) {
            shoot(p);
            setTps(0);
        }
    }
}
