package application.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

public class Arrow {

    private IntegerProperty x, y;
    private Timeline path;
    private TileMap mapTile;
    private int range, distanceTravelled;
    private int dx, dy;

    public Arrow(MainCharacter launcher, MainCharacter target, TileMap map) {
        this.x = new SimpleIntegerProperty(launcher.getX());
        this.y = new SimpleIntegerProperty(launcher.getY());
        mapTile = map;
        range = launcher.getRangeForAttack() * mapTile.getPIXELBLOCK();
        initPath(launcher, target);
        path();
    }

    public void initPath(MainCharacter launcher, MainCharacter target) {
        distanceTravelled = 0;
        if (x.getValue() - target.getX() < 0) dx = 1;
        else dx = -1;
        if (y.getValue() - target.getY() < 0) dy = 1;
        else dy = -1;
        path = new Timeline();
		path.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.014),(ev ->{
            if (distanceTravelled <= range) {
                if (x.getValue() - target.getX() != 0)
                    x.set(dx);
                if (y.getValue() - target.getY() != 0)
                    y.set(dy);
            }
            distanceTravelled++;

		}));
		path.getKeyFrames().add(kf);
    }
    public void path() {
        path.play();
    }

    

}