package application.modele;

public class Squeleton extends Enemy{
    
    private final int att = 5;
    private final static int speed = 1, range = 10, jumpMax = 4;
    private Bow bow;
    private static final int RANGEFORATTACKSQUELETON = 4 * 32;

    public Squeleton(int x, int y, TileMap map, int att, int pv,Environment env) {
        super(x, y, map, att, pv, range, jumpMax,env);
        bow = new Bow(this, map, getEnvironment());
        setVIT(speed);
        setRangeForAttack(RANGEFORATTACKSQUELETON);
    }

    public void shoot(MainCharacter target) {
        bow.shoot(target);
    }

    public void attacking(MainCharacter p) {
        if (isNear(p) && getTps() >= getDelayAttack()) {
            shoot(p);
            setTps(0);
        }
    }


    

}

