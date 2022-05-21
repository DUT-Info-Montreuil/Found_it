package application.vue;

import application.modele.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class InterfacePlayerVue {
    
    private Player player;
    private Pane mapPane;
    private Label hp;

    public InterfacePlayerVue(Player p, Pane pane) {
        player = p;
        mapPane = pane;
        addInterfaceHP();
    }

    public void addInterfaceHP() {
        hp = new Label(String.valueOf(player.getHp()));
        hp.setTextFill(Color.web("#FFFFFF"));
        hp.setAlignment(Pos.TOP_RIGHT);
        player.getHpProperty().addListener((obs,old,nouv)-> {
			hp.setText(String.valueOf(player.getHp()));
		});
        mapPane.getChildren().add(hp);
    }

}
