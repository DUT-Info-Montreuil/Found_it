package application.vue;


import application.modele.MainCharacter;
import application.modele.Player;
import application.modele.Slime;
import application.modele.Zombie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CharacterVue {

	private Pane mapPane;
	private MainCharacter c;
	private ImageView character;

	private Image characterRight;
	private Image characterLeft;

	
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
			characterRight = new Image("application/vue/character/PersonnageMario.png");
			characterLeft = new Image("application/vue/character/PersonnageMarioReverse.png");;
		}
		if (c instanceof Zombie) {
			characterRight = new Image("application/vue/ennemies/zombie.png");
			characterLeft = new Image("application/vue/ennemies/zombieReverse.png");
		}
		if (c instanceof Slime) {
			characterRight = new Image("application/vue/ennemies/slime.png");
			characterLeft = new Image("application/vue/ennemies/slime.png");
		}
		character = new ImageView(characterRight);
	}

	private void addCharacterInMap() {
		mapPane.getChildren().add(character);
	}
	
	public ImageView getCharacter() {
		return character;
	}
	
	public void editCharacterImage(Image i) {
		character.setImage(i);
	}
	
	private void linkCharacter() {
		character.translateXProperty().bind(c.getXProperty());
		character.translateYProperty().bind(c.getYProperty());
		c.getDirectionnalXProperty().addListener((obs,old,nouv)-> {
			if (c.getDirectionnalX() == - 1)
				character.setImage(characterLeft);	
			else
				character.setImage(characterRight);
		});
	}


	// public void linkPlayer(MainCharacter p) {
	// 	ChangeListener<Number> obs1 = 
	// 			((obs,old,nouv)-> {
	// 				if (p.getDirectionnalX() == - 1) {
	// 					character.setImage(characterLeft);
	// 					if (p.getPelle()!= null) 
	// 						editToolsImage(pickaxeLeft);
	// 					else
	// 						editToolsImage(null);
	// 					tools.setX(-8);
	// 				}
	// 				else {
	// 					character.setImage(characterRight);
	// 					if (p.getPelle()!= null)
	// 						editToolsImage(pickaxeRight);
	// 					else
	// 						editToolsImage(null);
	// 					tools.setX(25);			
	// 				}
	// 			}	
	// 	);
	// 	p.getDirectionnalXProperty().addListener(obs1);
	// 	tools.setY(10);
	// 	tools.layoutXProperty().bind(c.getXProperty());
	// 	tools.layoutYProperty().bind(c.getYProperty());
	// }
}
