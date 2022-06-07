package application.vue;


import application.modele.Enemy;
import application.modele.MainCharacter;
import application.modele.Player;
import application.modele.Slime;
import application.modele.Squeleton;
import application.modele.Zombie;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class CharacterVue {

	private Pane mapPane;
	private MainCharacter c;
	private ImageView character;
	private Timeline loopImage;

	
	public CharacterVue(Pane mapPane, Player p) {
		this.mapPane = mapPane;
		c = p;
		initImageCharacter();
		addCharacterInMap();
		linkCharacter();
		new InterfacePlayerVue(p, mapPane);
		initLoopImage();
		loopImage();
	}

	public CharacterVue(Pane mapPane, MainCharacter p) {
		this.mapPane = mapPane;
		c = p;
		initImageCharacter();
		addCharacterInMap();
		linkCharacter();
		// initLoopImage();
		// loopImage();
	}
	
	public void initImageCharacter() {
		if (c instanceof Player) {
			// character = new ImageView(new Image("application/vue/character/PersonnageMario.png"));
			character = new ImageView(new Image("application/vue/character/SwordsManAnim.png"));
			character.setViewport(new Rectangle2D(0, 0, 32, 32));
		}
		if (c instanceof Zombie) {
			character = new ImageView(new Image("application/vue/ennemies/zombie.png"));
		}
		if (c instanceof Slime) {
			character = new ImageView(new Image("application/vue/ennemies/slime.png"));
		}
		if (c instanceof Squeleton)
			character = new ImageView(new Image("application/vue/ennemies/squeleton.png"));
		character.setId(c.getId());
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

	public void nextImage(int ligne, int nbImage) {
		if (character.getViewport().getMinY() == ligne * 32) {
			if (character.getViewport().getMaxX() >= 32 * nbImage)
				character.setViewport(new Rectangle2D(0, character.getViewport().getMinY(), 32, 32));
			else
				character.setViewport(new Rectangle2D(character.getViewport().getMaxX(), character.getViewport().getMinY(), 32, 32));
		}
		else {
			character.setViewport(new Rectangle2D(0, ligne * 32, 32, 32));
		}
	}

	private void initLoopImage() {
		loopImage = new Timeline();
		loopImage.setCycleCount(Timeline.INDEFINITE);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.2),(ev ->{
			if (c.isJumpingBoolean())
				nextImage(2,2);
			else if (c.isFallingBoolean())
				nextImage(3,2);
			else if (c.getRightPressed() || c.getLeftPressed() && !(c.getLeftPressed() && c.getRightPressed()))
				nextImage(1,6);
			else
				nextImage(0,10);
		}));
		loopImage.getKeyFrames().add(kf);
	}
	private void loopImage() {
		loopImage.play();
	}

}
