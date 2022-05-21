package application.modele;

public class Squeleton extends Enemy{
    
    private final int att = 5;
    private final static int speed = 1;
    private final static int range = 10;
    private final static int jumpMax = 4;

    public Squeleton(int x, int y, TileMap map, int att, int pv) {
        super(x, y, map, att, pv, range, jumpMax);
        setVIT(speed);
    }

    

}

