package application.controller;

import application.modele.Player;
import application.modele.TileMap;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;

public class Camera extends ParallelCamera {
    
    private Scene scene;
    private Player player;
    private TileMap mapTile;
    private int radius = 800;

    public Camera(Scene scene, Player p, TileMap map) {
        player = p;
        linkWithPlayer();
        scene.setCamera(this);
    }
    public void linkWithPlayer() {
        player.getXProperty().addListener((obs,old,nouv)-> {
            if (player.getX() - radius < 0) 
                this.setTranslateX(0);
            else
                this.setTranslateX(player.getX() - radius);
        });
        player.getYProperty().addListener((obs,old,nouv)-> {
            if (player.getY() - radius < 0) 
                this.setTranslateY(0);
            else
                this.setTranslateY(player.getY() - radius);
        });
    }
}
