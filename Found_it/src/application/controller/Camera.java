package application.controller;

import application.modele.Player;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;

public class Camera extends ParallelCamera {
    
    private Scene scene;
    private Player player;
    private int radius = 150;

    public Camera(Scene scene, Player p) {
        player = p;
        linkWithPlayer();
        scene.setCamera(this);
    }
    public void linkWithPlayer() {
        player.getXProperty().addListener((obs,old,nouv)-> {
            this.setTranslateX(player.getX() - radius);
        });
        // player.getYProperty().addListener((obs,old,nouv)-> {
        //     this.setTranslateY(player.getY() - radius);
        // });
    }
}
