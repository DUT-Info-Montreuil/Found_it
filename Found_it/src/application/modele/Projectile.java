package application.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
public class Projectile {
    
    private IntegerProperty x, y;
    private TileMap mapTile;
    private int range, distanceTravelled;
    private int dx, dy;
    private MainCharacter target;

    public Projectile(MainCharacter launcher, MainCharacter target, TileMap map) {
        this.x = new SimpleIntegerProperty(launcher.getX());
        this.y = new SimpleIntegerProperty(launcher.getY());
        mapTile = map;
        range = launcher.getRangeForAttack() * mapTile.getPIXELBLOCK();
        this.target = target;
    }

    public int getX() {
        return x.getValue();
    }
    public MainCharacter getTarget() {
        return target;
    }
    public int getY() {
        return y.getValue();
    }
    public IntegerProperty getXProperty() {
        return x;
    }
    public IntegerProperty getYProperty() {
        return y;
    }

    public void update(MainCharacter target) {
        if (distanceTravelled <= range) {
            if (x.getValue() - target.getX() != 0)
                x.set(getX()+dx);
            if (y.getValue() - target.getY() != 0)
                y.set(getY() + dy);
        }
        else {
            if(mapTile.wasTransparent(mapTile.getCodeTuile(getX(), getY() + 1)))
                y.set(getY()+1);
        }
        distanceTravelled++;
    }
}
