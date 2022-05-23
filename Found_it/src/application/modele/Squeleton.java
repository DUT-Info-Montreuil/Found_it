package application.modele;

public class Squeleton extends Enemy{
    
    private final int att = 5;
    private final static int speed = 1, range = 10, jumpMax = 4;

    public Squeleton(int x, int y, TileMap map, int att, int pv) {
        super(x, y, map, att, pv, range, jumpMax);
        setVIT(speed);
    }

    

}

