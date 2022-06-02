package application.modele;

public class Zombie extends Enemy{
    
    private final int att = 5;
    private final static int speed = 1, range = 10, jumpMax = 4;
    
    public Zombie(int x, int y, TileMap map, int att, int pv,Environment env) {
        super(x, y, map, att, pv, range, jumpMax,env);
        setVIT(speed);
    }

    

}
