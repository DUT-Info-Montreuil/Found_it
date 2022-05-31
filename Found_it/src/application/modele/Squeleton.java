package application.modele;

import application.modele.weapon.Bow;

public class Squeleton extends Enemy{
    
    private final int att = 5;
    private final static int speed = 1, range = 10, jumpMax = 4;
    private Bow bow;

    public Squeleton(int x, int y, TileMap map, int att, int pv) {
        super(x, y, map, att, pv, range, jumpMax);
        setVIT(speed);
    }

    public void shoot(MainCharacter target) {
        bow.shoot(target);
    }

    

}

