package application.modele;


public class Slime extends Enemy{

    private final int att = 5;
    private final static int speed = 1, range = 10, jumpMax = 4;

    public Slime(int x, int y, TileMap map, int att, int pv) {
        super(x, y, map, att, pv, range,jumpMax);
        setVIT(speed);
    }





}
