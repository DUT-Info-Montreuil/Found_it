package application.modele;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Slime extends Enemy{

    private final int att = 5;
    private final static int speed = 1;
    private final static int range = 10;
    private final static int jumpMax = 4;
    private Timeline slimeJump;

    public Slime(int x, int y, TileMap map, int att, int pv) {
        super(x, y, map, att, pv, range,jumpMax);
        setVIT(speed);
        slimeJump();
    }

    public void slimeJump() {
        initSlimeJump();
        slimeJump.play();
    }

    public void initSlimeJump() {
		slimeJump = new Timeline();
		slimeJump.setCycleCount(slimeJump.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.014),(ev ->{
            if (!isJumpingBoolean())
                jump();
		}));
		slimeJump.getKeyFrames().add(kf);
    }


}
