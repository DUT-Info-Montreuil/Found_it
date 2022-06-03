package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
public class Projectile {
    
    private IntegerProperty x, y;
    private int yTarget;
    private Environment environment;
    private TileMap mapTile;
    private int range, distanceTravelled;
    private IntegerProperty dx, dy;
    private MainCharacter launcher;
    private MainCharacter target;
    private String id;
    private static int cpt = 0;

    public Projectile(MainCharacter launcher, MainCharacter target, TileMap map, Environment env) {
        this.x = new SimpleIntegerProperty(launcher.getX());
        this.y = new SimpleIntegerProperty(launcher.getY());
        mapTile = map;
        range = launcher.getRangeAttack() * mapTile.getPIXELBLOCK();
        this.launcher = launcher;
        this.target = target;
        if (getX() - target.getX() <= 0) dx = new SimpleIntegerProperty(2);
        else dx = new SimpleIntegerProperty(-2);
        if (getY() - target.getY() <= 0) dy = new SimpleIntegerProperty(1);
        else dy = new SimpleIntegerProperty(-1);
        yTarget = target.getY();
        environment = env;
        id = "P" + cpt++;
    }

    public int getX() {
        return x.getValue();
    }
    public String getId() {
        return id;
    }
    public int getY() {
        return y.getValue();
    }

    public int getDX() {
        return dx.getValue();
    }
    public int getDY() {
        return dy.getValue();
    }
    public IntegerProperty getXProperty() {
        return x;
    }
    public IntegerProperty getYProperty() {
        return y;
    }

    public IntegerProperty getDyProperty() {
        return dy;
    }


    public void update() {
        if (getX() <= target.getXRight() && getX() >= target.getX() && getY() <= target.getYBOT() && getY() >= target.getY()) {
            launcher.sendDamage(target);
            environment.removedProjectile(this);
        }
        if (distanceTravelled <= range) {
            moveAxeX();
            if (y.getValue() - yTarget != 0)
                moveAxeY();
            else 
                dy.set(0);
        }
        else {
            dy.set(1);
            moveAxeX();
            moveAxeY();
        }
        distanceTravelled+= Math.abs(getDX());
    }

    public void moveAxeX() {
        if (mapTile.wasTransparent(mapTile.getCodeTuile(getX() + getDX(), getY())))
            x.set(getX()+getDX());
        else
            environment.removedProjectile(this);
    }

    public void moveAxeY() {
        if (mapTile.wasTransparent(mapTile.getCodeTuile(getX(), getY() + getDY())))
            y.set(getY() + getDY());
        else {
            environment.removedProjectile(this);
        }
    }
}
