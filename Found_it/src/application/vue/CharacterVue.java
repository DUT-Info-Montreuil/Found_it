package application.vue;

import application.modele.Player;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CharacterVue {

	private Pane mapPane;
	private ImageView character;
	private Player p;
	
	public CharacterVue(Pane mapPane, Player p) {
		this.mapPane = mapPane;
		character = new ImageView(new Image("application/vue/tilset/PersonnageMario.png"));
		this.p = p;
		addCharacterInMap();
		linkCharacter();
	}
	
	private void addCharacterInMap() {
		mapPane.getChildren().add(character);
	}
	
	public ImageView getCharacter() {
		return character;
	}
	private void linkCharacter() {
		character.translateXProperty().bind(p.getXProperty());
		character.translateYProperty().bind(p.getYProperty());
	}
}
