package application.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Projectile {
    
    private IntegerProperty x, y;
    private Timeline path;
    private TileMap mapTile;
    private int range, distanceTravelled;
    private int dx, dy;

    public Projectile(MainCharacter launcher, MainCharacter target, TileMap map) {
        this.x = new SimpleIntegerProperty(launcher.getX());
        this.y = new SimpleIntegerProperty(launcher.getY());
        mapTile = map;
        range = launcher.getRangeForAttack() * mapTile.getPIXELBLOCK();
        initPath(target);
        path();
    }

    public int getX() {
        return x.getValue();
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

    public void initPath(MainCharacter target) {
        distanceTravelled = 0;
        path = new Timeline();
		path.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.014),(ev ->{
            if (distanceTravelled <= range) {
                if (x.getValue() - target.getX() != 0)
                    x.set(getX()+dx);
                if (y.getValue() - target.getY() != 0)
                    y.set(getY() + dy);
            }
            else {
                if(mapTile.wasTransparent(mapTile.getCodeTuile(getX(), getY() + 1)))
			        y.set(getY()+1);
                else 
                    path.stop();
            }
            distanceTravelled++;
            if (distanceTravelled%100 == 0)
                System.out.println(distanceTravelled);

		}));
		path.getKeyFrames().add(kf);
    }
    public void path() {
        path.play();
    }

}
