package application.vue;

import application.controller.Camera;
import application.modele.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InterfacePlayerVue {
    
    private Player player;
    private Pane mapPane;
    private Camera cam;
    private Label hp;

    public InterfacePlayerVue(Player p, Pane pane, Camera cam) {
        player = p;
        mapPane = pane;
        this.cam = cam;
        addInterfaceHP();
    }

    public void addInterfaceHP() {
        hp = new Label(String.valueOf(player.getHp()));
        hp.setTextFill(Color.web("#FFFFFF"));
        hp.setAlignment(Pos.TOP_RIGHT);
        player.getHpProperty().addListener((obs,old,nouv)-> {
			hp.setText(String.valueOf(player.getHp()));
		});
        hp.translateXProperty().bind(cam.translateXProperty());
        hp.translateYProperty().bind(cam.translateYProperty());
        mapPane.getChildren().add(hp);
    }

}
