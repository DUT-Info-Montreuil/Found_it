package application.vue;


import application.modele.MainCharacter;
import application.modele.Player;
import application.modele.Slime;
import application.modele.Squeleton;
import application.modele.Zombie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CharacterVue {

	private Pane mapPane;
	private MainCharacter c;
	private ImageView character;

	
	public CharacterVue(Pane mapPane, Player p) {
		this.mapPane = mapPane;
		c = p;
		initImageCharacter();
		addCharacterInMap();
		linkCharacter();
		new InterfacePlayerVue(p, mapPane);
	}

	public CharacterVue(Pane mapPane, MainCharacter p) {
		this.mapPane = mapPane;
		c = p;
		initImageCharacter();
		addCharacterInMap();
		linkCharacter();
	}
	
	public void initImageCharacter() {
		if (c instanceof Player) {
			character = new ImageView(new Image("application/vue/character/PersonnageMario.png"));
		}
		if (c instanceof Zombie) {
			character = new ImageView(new Image("application/vue/ennemies/zombie.png"));
		}
		if (c instanceof Slime) {
			character = new ImageView(new Image("application/vue/ennemies/slime.png"));
		}
		if (c instanceof Squeleton)
			character = new ImageView(new Image("application/vue/ennemies/squeleton.png"));
	}

	private void addCharacterInMap() {
		mapPane.getChildren().add(character);
	}
	
	public ImageView getCharacter() {
		return character;
	}
	
	
	private void linkCharacter() {
		character.translateXProperty().bind(c.getXProperty());
		character.translateYProperty().bind(c.getYProperty());
		c.getDirectionnalXProperty().addListener((obs,old,nouv)-> {character.setScaleX(c.getDirectionnalX());});
	}

}
